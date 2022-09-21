package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.UserDTO;

import java.util.UUID;

public interface UserService {
    ApiResponse<?> getAllUserPageable(Integer page, Integer size);

    ApiResponse<?> getAllUser();

    ApiResponse<?> getByIdUser(UUID id);

    ApiResponse<?> createUser(UserDTO userDTO);

    ApiResponse<?> updateUser(UUID id, UserDTO userDTO);

    ApiResponse<?> deleteUser(UUID id);

    ApiResponse<?> getUser();

    ApiResponse<?> getByBranchId(UUID id);
}
