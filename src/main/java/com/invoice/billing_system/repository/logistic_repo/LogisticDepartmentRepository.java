package com.invoice.billing_system.repository.logistic_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.billing_system.model.logistic.LogisticDepartment;

@Repository
public interface LogisticDepartmentRepository extends JpaRepository<LogisticDepartment, String> {

    boolean existsByPlantCode(String plantCode);
}
