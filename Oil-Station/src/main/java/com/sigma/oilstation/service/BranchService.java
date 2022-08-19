package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.BranchDTO;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface BranchService {
    ApiResponse<?> getAllPageable(Integer page, Integer size);

    ApiResponse<?> getAll();

    ApiResponse<?> getById(UUID id);

    ApiResponse<?> create(BranchDTO branchDTO);

    ApiResponse<?> edit(UUID id, BranchDTO branchDTO);

    ApiResponse<?> delete(UUID id);
}
