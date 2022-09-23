package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findAllByDeletedIsFalse();

    Page<Measurement> findAllByDeletedIsFalse(Pageable pageable);
}
