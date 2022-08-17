package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Supplier;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.SupplierMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.repository.SupplierRepository;
import com.sigma.oilstation.service.SupplierService;
import com.sigma.oilstation.utils.CommandUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepo;

    private final SupplierMapper supplierMapper;

    @Override
    public ApiResponse<?> addSupplier(String name) {
        Optional<Supplier> optionalSupplier = supplierRepo.findByNameAndDeletedIsFalse(name);
        if (optionalSupplier.isPresent())
            return ApiResponse.errorResponse("Such a supplier already exist");
        supplierRepo.save(new Supplier(name));
        return ApiResponse.successResponse("Successfully saved");
    }

    @Override
    public ApiResponse<?> getAllSuppliers(Integer page, Integer size) {
        Page<Supplier> supplierPage;
        try {
            supplierPage = supplierRepo.findAllByDeletedIsFalse(CommandUtils.simplePageable(page, size));
        } catch (PageSizeException e) {
            return ApiResponse.errorResponse(e.getMessage());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("suppliers", supplierMapper.toGetDTOList(supplierPage.getContent()));
        response.put("currentPage", supplierPage.getNumber());
        response.put("totalItems", supplierPage.getTotalElements());
        response.put("totalPages", supplierPage.getTotalPages());
        return ApiResponse.successResponse("All suppliers with page", response);
    }

    @Override
    public ApiResponse<?> getSupplierById(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findByDeletedIsFalseAndId(id);
        if (optionalSupplier.isEmpty())
            return ApiResponse.errorResponse("Such a supplier does not exist");
        return ApiResponse.successResponse("Success", supplierMapper.toGetDTO(optionalSupplier.get()));
    }

    @Override
    public ApiResponse<?> editSupplierById(Long id, String name) {
        Optional<Supplier> optionalSupplier = supplierRepo.findByDeletedIsFalseAndId(id);
        if (optionalSupplier.isEmpty())
            return ApiResponse.errorResponse("Such a supplier does not exist");
        Supplier supplier = optionalSupplier.get();
        supplier.setName(name);
        supplierRepo.save(supplier);
        return ApiResponse.successResponse("Successfully updated");
    }

    @Override
    public ApiResponse<?> deleteSupplierById(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findByDeletedIsFalseAndId(id);
        if (optionalSupplier.isEmpty())
            return ApiResponse.errorResponse("Such a supplier does not exist");
        Supplier supplier = optionalSupplier.get();
        supplier.setDeleted(true);
        supplierRepo.save(supplier);
        return ApiResponse.successResponse("Successfully deleted");
    }
}
