package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Branch;
import com.sigma.oilstation.entity.FuelReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface FuelReportRepository extends JpaRepository<FuelReport, UUID> {
    FuelReport findByActiveShiftTrueAndEmployeeBranchIdAndFuel_Id(UUID employee_branch_id, UUID fuel_id);

    Page<FuelReport> findAllByReportTimeIsBetween(Timestamp startTime, Timestamp endTime, Pageable pageable);

    List<FuelReport> findAllByEmployeeBranchId(Pageable pageable, UUID employee_branch_id);

    Page<FuelReport> findAllByEmployeeBranchIdAndReportTimeIsBetween(UUID employee_branch_id, Timestamp startTime, Timestamp endTime, Pageable pageable);

    List<FuelReport> findAllByActiveShiftIsTrue();

    List<FuelReport> findAllByActiveShiftIsTrueAndEmployee_Branch(Branch employee_branch);

}
