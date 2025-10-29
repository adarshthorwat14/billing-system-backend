package com.invoice.billing_system.service.invoice_serv;

import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.model.distributor.ProductRequestItem;
import com.invoice.billing_system.model.invoice.Invoice;
import com.invoice.billing_system.model.invoice.InvoiceItem;
import com.invoice.billing_system.model.invoice_dto.DistributorClientInvoiceDTO;
import com.invoice.billing_system.model.invoice_dto.InvoiceItemRequest;
import com.invoice.billing_system.model.invoice_dto.InvoiceRequest;
import com.invoice.billing_system.model.invoice_dto.PaymentStatusDTO;
import com.invoice.billing_system.model.invoice_dto.ProductSummaryDTO;
import com.invoice.billing_system.model.plant.Plant;
import com.invoice.billing_system.model.product.Product;

import com.invoice.billing_system.repository.client_repo.ClientRepository;
import com.invoice.billing_system.repository.distributor_repo.DistributorRepository;
import com.invoice.billing_system.repository.distributor_repo.ProductRequestItemRepositorynew;
import com.invoice.billing_system.repository.distributor_repo.ProductRequestRepository;
import com.invoice.billing_system.repository.invoice_repo.InvoiceItemRepository;
import com.invoice.billing_system.repository.invoice_repo.InvoiceRepository;
import com.invoice.billing_system.repository.plant.PlantRepository;
import com.invoice.billing_system.repository.product_repo.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private ProductRequestItemRepositorynew productRequestItemRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private ProductRequestRepository productRequestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ✅ Create Invoice
    @Transactional
    public Invoice createInvoice(InvoiceRequest invoiceRequest) {
        Long nextVal = jdbcTemplate.queryForObject("SELECT nextval('invoice_id_seq')", Long.class);
        String invoiceId = String.format("IN%05d", nextVal);

        Client client = clientRepository.findById(invoiceRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Distributor distributor = distributorRepository.findById(invoiceRequest.getDistributorId())
                .orElseThrow(() -> new RuntimeException("Distributor not found"));

        Plant plant = plantRepository.findById(invoiceRequest.getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setClient(client);
        invoice.setDistributor(distributor);
        invoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setPaymentStatus(invoiceRequest.getPaymentStatus());
        invoice.setPaymentMethod(invoiceRequest.getPaymentMethod());
        invoice.setNotes(invoiceRequest.getNotes());
        invoice.setGeneratedBy(invoiceRequest.getGeneratedBy());
        invoice.setGlobalDiscount(invoiceRequest.getGlobalDiscount());
        invoice.setGlobalTax(invoiceRequest.getGlobalTax());
        invoice.setPlant(plant);

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        double subtotal = 0.0;

        for (InvoiceItemRequest itemRequest : invoiceRequest.getInvoiceItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Validate and reduce stock
            List<ProductRequestItem> stockItems = productRequestItemRepository
                    .findAvailableStockForProductAndDistributor(invoiceRequest.getDistributorId(), product.getProductId());

            int qtyToAllocate = itemRequest.getQuantity();
            for (ProductRequestItem stockItem : stockItems) {
                int available = stockItem.getApprovedQuantity() - stockItem.getQuantityUsed();
                if (available <= 0) continue;

                int reduce = Math.min(qtyToAllocate, available);
                stockItem.setQuantityUsed(stockItem.getQuantityUsed() + reduce);
                qtyToAllocate -= reduce;
                productRequestItemRepository.save(stockItem);

                if (qtyToAllocate == 0) break;
            }

            if (qtyToAllocate > 0) {
                throw new RuntimeException("Not enough allocated stock for product: " + product.getName());
            }

            // Calculate item subtotal
            double base = itemRequest.getPrice() * itemRequest.getQuantity();
            double itemDiscount = base * itemRequest.getDiscount() / 100.0;
            double itemTax = (base - itemDiscount) * itemRequest.getTaxRate() / 100.0;
            double itemSubtotal = base - itemDiscount + itemTax;

            InvoiceItem item = new InvoiceItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(itemRequest.getPrice());
            item.setDiscount(itemRequest.getDiscount());
            item.setTaxRate(itemRequest.getTaxRate());
            item.setDescription(itemRequest.getDescription());
            item.setInvoice(invoice);
            item.setSubtotal(itemSubtotal);

            invoiceItems.add(item);
            subtotal += itemSubtotal;
        }

        // Global discount and tax
        double discountAmount = subtotal * invoice.getGlobalDiscount() / 100.0;
        double afterDiscount = subtotal - discountAmount;
        double taxAmount = afterDiscount * invoice.getGlobalTax() / 100.0;
        double totalPay = afterDiscount + taxAmount;

        invoice.setInvoiceItems(invoiceItems);
        invoice.setTotalAmount(subtotal);
        invoice.setDiscount(discountAmount);
        invoice.setTaxAmount(taxAmount);
        invoice.setTotalPay(totalPay);

        return invoiceRepository.save(invoice);
    }

    // ✅ Fetch invoices by distributor
    public List<DistributorClientInvoiceDTO> getInvoicesByDistributor(String distributorId) {
        List<Invoice> invoices = invoiceRepository.findByDistributor_Id(distributorId);
        List<DistributorClientInvoiceDTO> dtoList = new ArrayList<>();

        for (Invoice invoice : invoices) {
            DistributorClientInvoiceDTO dto = new DistributorClientInvoiceDTO();
            dto.setInvoiceId(invoice.getInvoiceId());
            dto.setClientId(invoice.getClient().getId());
            dto.setClientName(invoice.getClient().getName());
            dto.setInvoiceDate(invoice.getInvoiceDate());
            dto.setTotalAmount(invoice.getTotalAmount());
            dto.setTotalPay(invoice.getTotalPay());
            dto.setGlobalTax(invoice.getGlobalTax());
            dto.setGlobalDiscount(invoice.getGlobalDiscount());
            dto.setPaymentStatus(invoice.getPaymentStatus());

            List<ProductSummaryDTO> products = new ArrayList<>();
            for (InvoiceItem item : invoice.getInvoiceItems()) {
                ProductSummaryDTO p = new ProductSummaryDTO();
                p.setProductName(item.getProduct().getName());
                p.setQuantity(item.getQuantity());
                p.setSubtotal(item.getSubtotal());
                p.setPrice(item.getPrice());
                products.add(p);
            }

            dto.setProducts(products);
            dtoList.add(dto);
        }

        return dtoList;
    }

    // ✅ Fetch all invoices
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    // ✅ Get single invoice by ID
    public Invoice getInvoiceById(String invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + invoiceId));
    }

    // ✅ Delete invoice
    public boolean deleteInvoiceById(String invoiceId) {
        if (invoiceRepository.existsById(invoiceId)) {
            invoiceRepository.deleteById(invoiceId);
            return true;
        }
        return false;
    }

    // ✅ Payment status list
    public List<PaymentStatusDTO> getAllClientPaymentStatuses() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(invoice -> {
            Client client = invoice.getClient();
            PaymentStatusDTO dto = new PaymentStatusDTO();
            dto.setInvoiceId(invoice.getInvoiceId());
            dto.setClientId(client.getId());
            dto.setClientName(client.getName());
            dto.setPaymentMethod(invoice.getPaymentMethod());
            dto.setPaymentStatus(invoice.getPaymentStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    // ✅ Update payment status
    public void updatePaymentStatus(String invoiceId, String newStatus) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setPaymentStatus(newStatus);
        invoiceRepository.save(invoice);
    }
}
