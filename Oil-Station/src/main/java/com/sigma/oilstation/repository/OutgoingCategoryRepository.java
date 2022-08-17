package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.OutgoingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutgoingCategoryRepository extends JpaRepository<OutgoingCategory, UUID> {
}
