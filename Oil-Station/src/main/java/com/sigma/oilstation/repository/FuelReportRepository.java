package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.FuelReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuelReportRepository extends JpaRepository<FuelReport, UUID> {

    FuelReport findByActiveShiftTrueAndDeviceId(UUID device_id);
}
