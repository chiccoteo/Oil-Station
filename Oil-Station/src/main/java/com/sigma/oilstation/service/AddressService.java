package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.AddressDTO;
import com.sigma.oilstation.payload.ApiResponse;

public interface AddressService {
    ApiResponse<?> getAll(Integer page, Integer size);

    ApiResponse<?> getById(Long id);

    ApiResponse<?> create(AddressDTO addressDTO);

    ApiResponse<?> update(AddressDTO addressDTO, Long id);

    ApiResponse<?> delete(Long id);
}
