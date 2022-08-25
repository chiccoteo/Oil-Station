package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.MeasurementController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeasurementControllerImpl implements MeasurementController {

    private final MeasurementService measurementService;

    @Override
    public HttpEntity<?> addMeasurement(String name) {
        ApiResponse<?> apiResponse = measurementService.addSupplier(name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAllMeasurements(Integer page, Integer size) {
        ApiResponse<?> apiResponse = measurementService.getAllMeasurements(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getMeasurementById(Long id) {
        ApiResponse<?> apiResponse = measurementService.getMeasurementById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> editMeasurementById(Long id, String name) {
        ApiResponse<?> apiResponse = measurementService.editMeasurementById(id, name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> deleteMeasurementById(Long id) {
        ApiResponse<?> apiResponse = measurementService.deleteMeasurementById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
