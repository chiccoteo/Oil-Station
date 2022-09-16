package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.NotificationController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.NotificationPostDTO;
import com.sigma.oilstation.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;

    @Override
    public HttpEntity<?> create(NotificationPostDTO notificationPostDTO) {
        ApiResponse<?> apiResponse = notificationService.create(notificationPostDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public HttpEntity<?> get() {
        ApiResponse<?> apiResponse = notificationService.get();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public HttpEntity<?> update(UUID id) {
        ApiResponse<?> apiResponse = notificationService.update(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> updateLimit(Long oilLimit) {
        ApiResponse<?> apiResponse = notificationService.updateLimit(oilLimit);
        return ResponseEntity.ok(apiResponse);
    }

}
