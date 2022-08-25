package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.RoleDTO;

public interface RoleService {
    ApiResponse<?> getAllPageable(Integer page, Integer size);

    ApiResponse<?> getAll();

    ApiResponse<?> getById(Long id);

    ApiResponse<?> create(RoleDTO roleDTO);

    ApiResponse<?> update(Long id, RoleDTO roleDTO);

    ApiResponse<?> delete(Long id);
}
