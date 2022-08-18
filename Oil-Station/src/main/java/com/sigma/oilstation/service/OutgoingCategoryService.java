package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;

import java.util.UUID;

public interface OutgoingCategoryService {
    ApiResponse<?> getAllOutgoingCategories(Integer page, Integer size);

    ApiResponse<?> getOutgoingCategoryById(UUID id);

}
