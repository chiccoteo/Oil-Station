package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.FuelReportController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import com.sigma.oilstation.service.FuelReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FuelReportControllerImpl implements FuelReportController {
    private final FuelReportService fuelReportService;

    @Override
    public HttpEntity<?> create(FuelReportPostDto fuelPostDto) {
        ApiResponse<?> response = fuelReportService.create(fuelPostDto);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @Override
    public HttpEntity<?> edit(FuelReportDto fuelReportDto) {
        ApiResponse<?> response = fuelReportService.edit(fuelReportDto);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @Override
    public HttpEntity<?> getById(UUID id) {
        ApiResponse<?> response = fuelReportService.getById(id);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @Override
    public HttpEntity<?> get() {
        ApiResponse<?> response = fuelReportService.get();
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @Override
    public HttpEntity<?> getPage(int page, int size) {
        ApiResponse<?> response = fuelReportService.get(page,size);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @Override
    public HttpEntity<?> getByBranch(UUID branchId) {
        return null;
    }

}
