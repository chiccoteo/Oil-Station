package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.IncomeFuel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.UUID;

public interface IncomeFuelRepository extends JpaRepository<IncomeFuel, UUID> {

    Page<IncomeFuel> findAllByIncomeTimeIsBetween(Timestamp startTime, Timestamp endTime, Pageable pageable);

    Page<IncomeFuel> findAllByEmployeeBranchId(UUID employee_branch_id, Pageable pageable);

    Page<IncomeFuel> findAllByEmployeeBranchIdAndIncomeTimeIsBetween(UUID employee_branch_id, Timestamp startTime, Timestamp endTime, Pageable pageable);
}
