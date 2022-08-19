package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelPostDto;
import com.sigma.oilstation.payload.FuelUpdateDto;

import java.util.UUID;

public interface FuelService {
    ApiResponse<?> addFuel(FuelPostDto fuelPostDto);

    ApiResponse<?> getByIdFuel(UUID id);

    ApiResponse<?> getAllFuel(Integer page, Integer size);

    ApiResponse<?> updateFuel(UUID id, FuelUpdateDto fuelUpdateDto);

    ApiResponse<?> deleteFuel(UUID id);
}
