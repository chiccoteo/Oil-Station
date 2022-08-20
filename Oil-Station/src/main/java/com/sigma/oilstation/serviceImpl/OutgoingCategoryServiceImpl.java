package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.OutgoingCategory;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.OutgoingCategoryMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.repository.OutgoingCategoryRepository;
import com.sigma.oilstation.service.OutgoingCategoryService;
import com.sigma.oilstation.utils.CommandUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutgoingCategoryServiceImpl implements OutgoingCategoryService {

    private final OutgoingCategoryRepository outgoingCategoryRepo;

    private final OutgoingCategoryMapper outgoingCategoryMapper;

    @Override
    public ApiResponse<?> getAllOutgoingCategories(Integer page, Integer size) {
        Page<OutgoingCategory> outgoingCategoryPage;
        try {
            outgoingCategoryPage = outgoingCategoryRepo.findAll(CommandUtils.simplePageable(page, size));
        } catch (PageSizeException e) {
            return ApiResponse.errorResponse(e.getMessage());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("outgoingCategories", outgoingCategoryMapper.toGetDTOList(outgoingCategoryPage.getContent()));
        response.put("currentPage", outgoingCategoryPage.getNumber());
        response.put("totalItems", outgoingCategoryPage.getTotalElements());
        response.put("totalPages", outgoingCategoryPage.getTotalPages());
        return ApiResponse.successResponse("All outgoingCategories with page", response);
    }

    @Override
    public ApiResponse<?> getOutgoingCategoryById(UUID id) {
        Optional<OutgoingCategory> optionalOutgoingCategory = outgoingCategoryRepo.findById(id);
        if (optionalOutgoingCategory.isEmpty())
            return ApiResponse.errorResponse("Such an outgoingCategory does not exist");
        return ApiResponse.successResponse("Success", outgoingCategoryMapper.toGetDTO(optionalOutgoingCategory.get()));
    }

    @Override
    public ApiResponse<?> getListOfOutgoingCategories() {
        return ApiResponse.successResponse("List of all OutgoingCategories", outgoingCategoryMapper.toGetDTOList(outgoingCategoryRepo.findAll()));
    }
}
