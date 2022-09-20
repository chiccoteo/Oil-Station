package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.FuelReportController;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import com.sigma.oilstation.service.FuelReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FuelReportControllerImpl implements FuelReportController {
    private final FuelReportService fuelReportService;

    @Override
    public HttpEntity<?> create(FuelReportPostDto fuelPostDto) {
        ApiResponse<?> response = fuelReportService.create(fuelPostDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @Override
    public HttpEntity<?> edit(FuelReportDto fuelReportDto) {
        ApiResponse<?> response = fuelReportService.edit(fuelReportDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getById(UUID id) {
        ApiResponse<?> response = fuelReportService.getById(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @Override
    public HttpEntity<?> get() {
        ApiResponse<?> response = fuelReportService.get();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getPage(int page, int size) {
        ApiResponse<?> response = fuelReportService.get(page, size);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getByBranch(UUID branchId, int page, int size) throws PageSizeException {
        ApiResponse<?> response = fuelReportService.getByBranchId(branchId, page, size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getDailyFuelReport(int page, int size) {
        ApiResponse<?> response = fuelReportService.getDailyFuelReport(page, size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getWeeklyFuelReport(int page, int size) {
        ApiResponse<?> response = fuelReportService.getWeeklyFuelReport(page, size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getMonthlyFuelReport(int page, int size) {
        ApiResponse<?> response = fuelReportService.getMonthlyFuelReport(page, size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getAnnualFuelReport(int page, int size) {
        ApiResponse<?> response = fuelReportService.getAnnuallyFuelReport(page, size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getInterimFuelReport(int page, int size, Date startDate, Date endDate) {
        ApiResponse<?> response = fuelReportService.getInterimFuelReport(page, size, startDate, endDate);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getFuelReportCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        ApiResponse<?> apiResponse = fuelReportService.getFuelReportCurrent(currentUser);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public HttpEntity<?> delete() {
        ApiResponse<?> apiResponse = fuelReportService.delete();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public HttpEntity<?> getDailyBranchFuelReport(int page, int size, UUID branchId) {
        ApiResponse<?> response = fuelReportService.getDailyBranchFuelReport(page, size, branchId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getWeeklyBranchFuelReport(int page, int size, UUID branchId) {
        ApiResponse<?> response = fuelReportService.getWeeklyBranchFuelReport(page, size, branchId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getMonthlyBranchFuelReport(int page, int size, UUID branchId) {
        ApiResponse<?> response = fuelReportService.getMonthlyBranchFuelReport(page, size, branchId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getAnnualBranchFuelReport(int page, int size, UUID branchId) {
        ApiResponse<?> response = fuelReportService.getAnnuallyBranchFuelReport(page, size, branchId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @Override
    public HttpEntity<?> getInterimBranchFuelReport(int page, int size, Date startDate, Date endDate, UUID branchId) {
        ApiResponse<?> response = fuelReportService.getInterimBranchFuelReport(page, size, branchId, startDate, endDate);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
