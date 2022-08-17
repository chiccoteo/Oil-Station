package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByNameAndDeletedIsFalse(String name);

    Optional<Supplier> findByDeletedIsFalseAndId(Long id);

    Page<Supplier> findAllByDeletedIsFalse(Pageable pageable);

}
