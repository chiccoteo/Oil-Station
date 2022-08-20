package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.RoleController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.RoleDTO;
import com.sigma.oilstation.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService service;

    @Override
    public HttpEntity<?> getAllPageable(Integer page, Integer size) {
        ApiResponse<?> apiResponse = service.getAllPageable(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAll() {
        ApiResponse<?> apiResponse = service.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getById(Long id) {
        ApiResponse<?> apiResponse = service.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> create(RoleDTO roleDTO) {
        ApiResponse<?> apiResponse = service.create(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> update(Long id, RoleDTO roleDTO) {
        ApiResponse<?> apiResponse = service.update(id,roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        ApiResponse<?> apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }
}
