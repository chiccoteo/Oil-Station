package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.BranchController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.BranchDTO;
import com.sigma.oilstation.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BranchControllerImpl implements BranchController {

    private final BranchService service;


    @Override
    public HttpEntity<?> getAllPageable(Integer page, Integer size) {
        ApiResponse<?> apiResponse = service.getAllPageable(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAll() {
        ApiResponse<?> apiResponse = service.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getById(UUID id) {
        ApiResponse<?> apiResponse = service.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> create(BranchDTO branchDTO) {
        ApiResponse<?> apiResponse = service.create(branchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> edit(UUID id, BranchDTO branchDTO) {
        ApiResponse<?> apiResponse = service.edit(id, branchDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> delete(UUID id) {
        ApiResponse<?> apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
