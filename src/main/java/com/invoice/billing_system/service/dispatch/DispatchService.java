package com.invoice.billing_system.service.dispatch;

import java.util.List;

import com.invoice.billing_system.model.dispatch.Dispatch;

public interface DispatchService {
    Dispatch createDispatch(Dispatch dispatch);
    
    List<Dispatch> getAllDispatches();
}
