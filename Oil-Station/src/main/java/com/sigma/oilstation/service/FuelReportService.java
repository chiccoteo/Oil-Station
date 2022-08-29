package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;

import java.util.Date;
import java.util.UUID;

public interface FuelReportService {
    ApiResponse<?> create(FuelReportPostDto fuelPostDto);

    ApiResponse<?> edit(FuelReportDto fuelReportDto);

    ApiResponse<?> getById(UUID id);

    ApiResponse<?> get();

    ApiResponse<?> get(int page, int size);

    ApiResponse<?> getByBranchId(UUID branchId);

    ApiResponse<?> getDailyFuelReport(Integer page, Integer size);

    ApiResponse<?> getWeeklyFuelReport(Integer page, Integer size);

    ApiResponse<?> getMonthlyFuelReport(Integer page, Integer size);

    ApiResponse<?> getAnnuallyFuelReport(Integer page, Integer size);

    ApiResponse<?> getInterimFuelReport(Integer page, Integer size, Date startDate,Date endDate);

    ApiResponse<?> getDailyBranchFuelReport(int page, int size, UUID branchId);

    ApiResponse<?> getWeeklyBranchFuelReport(int page, int size, UUID branchId);

    ApiResponse<?> getMonthlyBranchFuelReport(int page, int size, UUID branchId);

    ApiResponse<?> getAnnuallyBranchFuelReport(int page, int size, UUID branchId);

    ApiResponse<?> getInterimBranchFuelReport(int page, int size, UUID branchId, Date startDate, Date endDate);

}
