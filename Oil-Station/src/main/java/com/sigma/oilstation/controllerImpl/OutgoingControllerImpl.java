package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.OutgoingController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.OutgoingPostDTO;
import com.sigma.oilstation.service.OutgoingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OutgoingControllerImpl implements OutgoingController {

    private final OutgoingService outgoingService;

    @Override
    public HttpEntity<?> createOutgoing(OutgoingPostDTO outgoingPostDTO) {
        ApiResponse<?> apiResponse = outgoingService.createOutgoing(outgoingPostDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAllOutgoings(Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getAllOutgoings(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getOutgoingById(UUID id) {
        ApiResponse<?> apiResponse = outgoingService.getOutgoingById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> updateOutgoingById(UUID id, OutgoingPostDTO outgoingPostDTO) {
        ApiResponse<?> apiResponse = outgoingService.updateOutgoingById(id, outgoingPostDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> deleteOutgoingById(UUID id) {
        ApiResponse<?> apiResponse = outgoingService.deleteOutgoingById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
