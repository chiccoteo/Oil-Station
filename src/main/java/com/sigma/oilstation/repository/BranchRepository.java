package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    List<Branch> findAllByDeleteIsFalse(Sort createdDate);
    Page<Branch> findAllByDeleteIsFalse(Pageable pageable);
}
