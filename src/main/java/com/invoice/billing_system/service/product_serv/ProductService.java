package com.invoice.billing_system.service.product_serv;


import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.distributor.ProductRequest;
import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestDTO;
import com.invoice.billing_system.model.hsn.Hsn;
import com.invoice.billing_system.model.plant.Plant;
import com.invoice.billing_system.model.plant.PlantStock;
import com.invoice.billing_system.model.plant_dto.PlantStockItemDTO;
import com.invoice.billing_system.model.product.Product;
import com.invoice.billing_system.model.product_dto.ProductCreateDTO;
import com.invoice.billing_system.model.product_dto.ProductRequestWithStockDTO;
import com.invoice.billing_system.model.product_dto.ProductStockView;
import com.invoice.billing_system.repository.distributor_repo.ProductRequestRepository;
import com.invoice.billing_system.repository.hsn.HsnRepository;
import com.invoice.billing_system.repository.plant.PlantRepository;
import com.invoice.billing_system.repository.plant.PlantStockRepository;
import com.invoice.billing_system.repository.product_repo.ProductRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private PlantRepository plantRepository;
    
    @Autowired
    private HsnRepository hsnRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    
    @Autowired
    private PlantStockRepository plantStockRepository;
    
    
    private String nextProductId() {
        Long nextVal = jdbcTemplate.queryForObject("SELECT nextval('product_id_seq')", Long.class);
        return String.format("PR%05d", nextVal);
    }
    
    // Add stock for a product for a specific plant
    public void addStock(String productId, String plantId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        PlantStock stock = plantStockRepository.findByProductAndPlant(product, plant)
                .orElse(new PlantStock());

        stock.setProduct(product);
        stock.setPlant(plant);
        stock.setStockQuantity((stock.getStockQuantity() != null ? stock.getStockQuantity() : 0) + quantity);

        plantStockRepository.save(stock);
    }

    // Get stock for a product across all plants
    public List<PlantStock> getProductStock(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return plantStockRepository.findByProduct(product);
    }

    // Get stock for a plant
    public List<PlantStock> getStockByPlant(String plantId) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new RuntimeException("Plant not found"));
        return plantStockRepository.findByPlant(plant);
    }

    
    
    public String generateBarcode(String name, String productId) {
        String prefix = name.replaceAll("\\s+", "").toUpperCase();
        if (prefix.length() > 3) {
            prefix = prefix.substring(0, 3); // FAN from "Fan", WAT from "Water"
        }
        String serial = "0001"; // You can later add dynamic serial logic
        return prefix + "/" + productId + "/" + serial;
    }
    	
    @Transactional
    public Product createProduct(ProductCreateDTO dto) {

        // 1) Build Product (no plantStocks inside Product itself)
        Product product = new Product();

        // Assign productId if missing
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(nextProductId());
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setMrp(dto.getMrp());
        product.setRdp(dto.getRdp());
        product.setImg(dto.getImg());
        product.setBatchNumber(dto.getBatchNumber());
        product.setExpiryDate(dto.getExpiryDate());
        product.setMfgDate(dto.getMfgDate());
        product.setUnitOfMeasure(dto.getUnitOfMeasure());

        // HSN (by code)
        if (dto.getHsnCode() != null) {
            Hsn hsn = hsnRepository.findById(dto.getHsnCode())
                    .orElseThrow(() -> new RuntimeException("HSN not found: " + dto.getHsnCode()));
            product.setHsn(hsn);
        }

        // Generate barcode
        String barcode = generateBarcode(dto.getName(), product.getProductId());
        product.setBarcode(barcode);

        // Save product first so PK is available
        Product saved = productRepository.save(product);

        // 2) Handle PlantStocks separately (unidirectional)
        List<PlantStockItemDTO> incoming = Optional.ofNullable(dto.getPlantStocks())
                .orElseGet(List::of);

        Map<String, Integer> byPlant = new LinkedHashMap<>();
        for (PlantStockItemDTO item : incoming) {
            if (item.getPlantId() == null || item.getPlantId().isBlank()) continue;
            int qty = Optional.ofNullable(item.getStockQuantity()).orElse(0);
            if (qty < 0) {
                throw new RuntimeException("Stock cannot be negative for plant: " + item.getPlantId());
            }
            byPlant.merge(item.getPlantId(), qty, Integer::sum);
        }

        if (!byPlant.isEmpty()) {
            List<String> ids = new ArrayList<>(byPlant.keySet());
            List<Plant> plants = plantRepository.findByPlantIdIn(ids);
            Set<String> foundIds = plants.stream().map(Plant::getPlantId).collect(Collectors.toSet());

            List<String> missing = ids.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            if (!missing.isEmpty()) {
                throw new RuntimeException("Plant(s) not found: " + String.join(", ", missing));
            }

            List<PlantStock> toSave = new ArrayList<>();
            for (Plant plant : plants) {
                PlantStock ps = new PlantStock();
                ps.setProduct(saved);
                ps.setPlant(plant);
                ps.setStockQuantity(byPlant.get(plant.getPlantId()));
                toSave.add(ps);
            }
            plantStockRepository.saveAll(toSave);
        }

        return saved;
    }
    
    
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    
//    public Product updateProduct(String productId, Product updatedProduct) {
//        Product existingProduct = productRepository.findById(productId)
//            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
//
//        // other field updates...
//        existingProduct.setName(updatedProduct.getName());
//        existingProduct.setDescription(updatedProduct.getDescription());
//        existingProduct.setPrice(updatedProduct.getPrice());
//        existingProduct.setMrp(updatedProduct.getMrp());
//        existingProduct.setRdp(updatedProduct.getRdp());
//        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
//        // ... and others
//
//        // ✅ Fetch and set existing HSN object
//        if (updatedProduct.getHsn() != null && updatedProduct.getHsn().getCode() != null) {
//            Hsn hsn = hsnRepository.findById(updatedProduct.getHsn().getCode())
//                .orElseThrow(() -> new RuntimeException("HSN not found with Code: " + updatedProduct.getHsn().getCode()));
//            existingProduct.setHsn(hsn);
//        }
//
//        // ✅ Fetch and set existing Plant object
//        if (updatedProduct.getPlant() != null && updatedProduct.getPlant().getPlantId() != null) {
//            Plant plant = plantRepository.findById(updatedProduct.getPlant().getPlantId())
//                .orElseThrow(() -> new RuntimeException("Plant not found with ID: " + updatedProduct.getPlant().getPlantId()));
//            existingProduct.setPlant(plant);
//        }
//
//        // image & barcode updates
//        if (updatedProduct.getImg() != null && !updatedProduct.getImg().isEmpty()) {
//            existingProduct.setImg(updatedProduct.getImg());
//        }
//
//        // barcode re-generation (optional)
//        existingProduct.setBarcode(generateBarcode(existingProduct.getName(), productId));
//
//        return productRepository.save(existingProduct);
//    }
//

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
    
//    public List<Product> getProductsByPlant(String plantId) {
//        return productRepository.findByPlant_PlantId(plantId);
//    }

}

