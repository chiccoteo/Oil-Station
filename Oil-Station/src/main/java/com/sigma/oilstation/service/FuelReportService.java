package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;

import java.util.UUID;

public interface FuelReportService {
    ApiResponse<?> create(FuelReportPostDto fuelPostDto);

    ApiResponse<?> edit(FuelReportDto fuelReportDto);

    ApiResponse<?> getById(UUID id);

    ApiResponse<?> get();

    ApiResponse<?> get(int page, int size);
}
