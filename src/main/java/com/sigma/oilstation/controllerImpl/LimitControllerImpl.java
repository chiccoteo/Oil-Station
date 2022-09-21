package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.LimitController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitControllerImpl implements LimitController {

    private final LimitService limitService;

    @Override
    public HttpEntity<?> update(Long limit) {
        ApiResponse<?> apiResponse = limitService.update(limit);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public HttpEntity<?> get() {
        ApiResponse<?> apiResponse = limitService.get();
        return ResponseEntity.ok(apiResponse);
    }

}
