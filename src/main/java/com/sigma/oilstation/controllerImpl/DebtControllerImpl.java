package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.DebtController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.DebtPostDto;
import com.sigma.oilstation.payload.DebtUpdateDto;
import com.sigma.oilstation.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DebtControllerImpl implements DebtController {

    final private DebtService debtService;

    @Override
    public HttpEntity<?> addDebt(DebtPostDto debtPostDto) {
        ApiResponse<?> apiResponse = debtService.addDebt(debtPostDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getByIdDebt(UUID id) {
        ApiResponse<?> apiResponse = debtService.getByIdDebt(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAllDebtPageable(Integer page, Integer size) {
        ApiResponse<?> apiResponse = debtService.getAllDebtPageable(page,size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAllDebt() {
        ApiResponse<?> apiResponse = debtService.getAllDebt();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> updateDebt(UUID id, DebtUpdateDto debtUpdateDto) {
        ApiResponse<?> apiResponse = debtService.updateDebt(id, debtUpdateDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @Override
    public HttpEntity<?> deleteDebt(UUID id) {
        ApiResponse<?> apiResponse = debtService.deleteDebt(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
