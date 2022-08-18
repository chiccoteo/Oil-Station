package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.FuelController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelPostDto;
import com.sigma.oilstation.payload.FuelUpdateDto;
import com.sigma.oilstation.service.FuelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class FuelControllerImpl implements FuelController {

    private final FuelService fuelService;


    @Override
    public HttpEntity<?> addFuel(@RequestBody FuelPostDto fuelPostDto) {
        ApiResponse<?> apiResponse = fuelService.addFuel(fuelPostDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getByIdFuel(UUID id) {
        ApiResponse<?> apiResponse = fuelService.getByIdFuel(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAllFuel(Integer page, Integer size) {
        ApiResponse<?> apiResponse = fuelService.getAllFuel(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> updateFuel(UUID id, @RequestBody FuelUpdateDto fuelUpdateDto) {
        ApiResponse<?> apiResponse = fuelService.updateFuel(id,fuelUpdateDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> deleteFuel(UUID id) {
        ApiResponse<?> apiResponse = fuelService.deleteFuel(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
