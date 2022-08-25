package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Outgoing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.UUID;

public interface OutgoingRepository extends JpaRepository<Outgoing, UUID> {

    Page<Outgoing> findAllByOutgoingTimeIsBetween(Timestamp startOutgoingTime, Timestamp endOutgoingTime, Pageable pageable);

}
