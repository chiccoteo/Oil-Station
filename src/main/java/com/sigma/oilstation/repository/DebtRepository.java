package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Debt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DebtRepository extends JpaRepository<Debt, UUID> {
    Page<Debt> findAllByLenderIsNotNull(Pageable pageable);
    Page<Debt> findAllByBorrowerNotNull(Pageable pageable);
}
