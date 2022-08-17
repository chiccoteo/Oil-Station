package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.AddressController;
import com.sigma.oilstation.payload.AddressDTO;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressService service;

    @Override
    public HttpEntity<?> getAll(Integer page, Integer size) {
        ApiResponse<?> apiResponse = service.getAll(page,size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200: 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getById(Long id) {
        ApiResponse<?> apiResponse = service.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200: 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> create(AddressDTO addressDTO) {
        ApiResponse<?> apiResponse = service.create(addressDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201:409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> update(AddressDTO addressDTO, Long id) {
        ApiResponse<?> apiResponse = service.update(addressDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200: 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        ApiResponse<?> apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200: 404).body(apiResponse);
    }
}
