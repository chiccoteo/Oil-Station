package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;

public interface MeasurementService {
    ApiResponse<?> addSupplier(String name);

    ApiResponse<?> getAllMeasurements(Integer page, Integer size);

    ApiResponse<?> getMeasurementById(Long id);

    ApiResponse<?> editMeasurementById(Long id, String name);

    ApiResponse<?> deleteMeasurementById(Long id);
}
