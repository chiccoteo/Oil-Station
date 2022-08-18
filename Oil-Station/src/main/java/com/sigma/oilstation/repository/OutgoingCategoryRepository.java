package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.OutgoingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OutgoingCategoryRepository extends JpaRepository<OutgoingCategory, UUID> {

    Optional<OutgoingCategory> findByName(String name);

}
