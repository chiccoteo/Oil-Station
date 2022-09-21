package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.UserController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.UserDTO;
import com.sigma.oilstation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Override
    public HttpEntity<?> getAllPageable(Integer page, Integer size) {
        ApiResponse<?> apiResponse = service.getAllUserPageable(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAll() {
        ApiResponse<?> apiResponse = service.getAllUser();
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getByBranchId(UUID id) {
        ApiResponse<?> apiResponse = service.getByBranchId(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getById(UUID id) {
        ApiResponse<?> apiResponse = service.getByIdUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> create(UserDTO userDTO) {
        ApiResponse<?> apiResponse = service.createUser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> update(UUID id, UserDTO userDTO) {
        ApiResponse<?> apiResponse = service.updateUser(id,userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> delete(UUID id) {
        ApiResponse<?> apiResponse = service.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getUser() {
        ApiResponse<?> apiResponse = service.getUser();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public HttpEntity<?> getUsersByBranchId(UUID id) {
        ApiResponse<?> apiResponse = service.getUsersByBranchId(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
