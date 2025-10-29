package com.invoice.billing_system.controller.dispatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.dispatch.Dispatch;
import com.invoice.billing_system.service.dispatch.DispatchService;

@RestController
@RequestMapping("/api/dispatches")
//@CrossOrigin("*")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @PostMapping("/create")
    public Dispatch createDispatch(@RequestBody Dispatch dispatch) {
        return dispatchService.createDispatch(dispatch);
    }

    @GetMapping("/all")
    public List<Dispatch> getAllDispatches() {
        return dispatchService.getAllDispatches();
    }
}
