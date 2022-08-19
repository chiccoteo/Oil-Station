package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.OutgoingCategoryController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.service.OutgoingCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OutgoingCategoryControllerImpl implements OutgoingCategoryController {

    private final OutgoingCategoryService outgoingCategoryService;

    @Override
    public HttpEntity<?> getAllOutgoingCategories(Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingCategoryService.getAllOutgoingCategories(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getOutgoingCategoryById(UUID id) {
        ApiResponse<?> apiResponse = outgoingCategoryService.getOutgoingCategoryById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
