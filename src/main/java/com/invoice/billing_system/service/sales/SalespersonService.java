package com.invoice.billing_system.service.sales;

import java.util.List;

import com.invoice.billing_system.model.sales.Salesperson;

public interface SalespersonService {
    Salesperson createSalesperson(Salesperson salesperson);
    List<Salesperson> getAllSalespersons();
    Salesperson getSalespersonById(Long id);
    Salesperson updateSalesperson(Long id, Salesperson salesperson);
    void deleteSalesperson(Long id);
    Salesperson login(Long id, String password);
}
