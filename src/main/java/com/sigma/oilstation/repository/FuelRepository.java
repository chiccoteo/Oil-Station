package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FuelRepository extends JpaRepository<Fuel, UUID> {
    Optional<Fuel> findByType(String type);
}
