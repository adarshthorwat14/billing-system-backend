package com.invoice.billing_system.repository.dispatch;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.dispatch.Dispatch;

public interface DispatchRepository extends JpaRepository<Dispatch, String> {
}
