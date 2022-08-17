package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.SupplierController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SupplierControllerImpl implements SupplierController {

    private final SupplierService service;

    @Override
    public HttpEntity<?> addSupplier(String name) {
        ApiResponse<?> apiResponse = service.addSupplier(name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAllSuppliers(Integer page, Integer size) {
        ApiResponse<?> apiResponse = service.getAllSuppliers(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getSupplierById(Long id) {
        ApiResponse<?> apiResponse = service.getSupplierById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> editSupplierById(Long id, String name) {
        ApiResponse<?> apiResponse = service.editSupplierById(id, name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> deleteSupplierById(Long id) {
        ApiResponse<?> apiResponse = service.deleteSupplierById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
