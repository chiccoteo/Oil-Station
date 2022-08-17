package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Outgoing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutgoingRepository extends JpaRepository<Outgoing, UUID> {
}
