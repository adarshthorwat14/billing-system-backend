package com.invoice.billing_system.controller.hsn;


import com.invoice.billing_system.model.hsn.Hsn;
import com.invoice.billing_system.repository.hsn.HsnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hsn")
public class HsnController {

    @Autowired
    private HsnRepository hsnRepository;

    @GetMapping
    public List<Hsn> getAllHSNCodes() {
        return hsnRepository.findAll();
    }

    @PostMapping
    public Hsn createHSN(@RequestBody Hsn hsn) {
        return hsnRepository.save(hsn);
    }
}
