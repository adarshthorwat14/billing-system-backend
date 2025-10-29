package com.invoice.billing_system.controller.product;

import com.invoice.billing_system.model.product.Product;
import com.invoice.billing_system.model.product_dto.ProductCreateDTO;
import com.invoice.billing_system.model.product_dto.ProductRequestWithStockDTO;
import com.invoice.billing_system.repository.client_repo.ClientRepository;
import com.invoice.billing_system.repository.product_repo.ProductRepository;
import com.invoice.billing_system.service.distributor_serv.ProductRequestService;
import com.invoice.billing_system.service.product_serv.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductRequestService productRequestService;
    
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getProductCount() {
        long count = productRepository.count();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductCreateDTO dto) {
        Product saved = productService.createProduct(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }



    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id).orElse(null);
    }
    
//    @PutMapping("update/{productId}")
//    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody Product updatedProduct) {
//        Product product = productService.updateProduct(productId, updatedProduct);
//        return ResponseEntity.ok(product);
//    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Save resized image
            BufferedImage
            originalImage = ImageIO.read(file.getInputStream());

            // Resize to 300x300
            BufferedImage resizedImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, 300, 300, null);
            g.dispose();

            // Create uploads folder if not exist
            File uploadDir = new File("uploads");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Generate unique name
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File outputFile = new File(uploadDir, fileName);

            // Write resized image to disk
            ImageIO.write(resizedImage, "jpg", outputFile);

            // Return image path
            String imagePath = "http://localhost:8080/uploads/" + fileName;
            return ResponseEntity.ok(imagePath);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }
    
}

