package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.IncomeFuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncomeFuelRepository extends JpaRepository<IncomeFuel, UUID> {
}
