package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
