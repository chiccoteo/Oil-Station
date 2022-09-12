package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Debt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DebtRepository extends JpaRepository<Debt, UUID> {
    Page<Debt> findAllByLenderIsNotNullOrderByGivenAsc(Pageable pageable);
    Page<Debt> findAllByBorrowerNotNullOrderByGivenAsc(Pageable pageable);

    @Query(value = "select * from debt d join users u on d.lender_or_borrower_id= u.id\n" +
            "join branch b on u.branch_id = b.id where b.id=:branchId and d.lender_id IS NOT NULL order by d.given , d.created_date desc", nativeQuery = true)
    Page<Debt> findAllByLenderByBranch(Pageable pageable, UUID branchId);

    @Query(value = "select * from debt d join users u on d.lender_or_borrower_id= u.id\n" +
            "join branch b on u.branch_id = b.id where b.id=:branchId and d.borrower IS NOT NULL order by d.given , d.created_date desc", nativeQuery = true)
    Page<Debt> findAllByBorrowerByBranch(Pageable pageable, UUID branchId);
}
