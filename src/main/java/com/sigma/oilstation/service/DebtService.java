package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.DebtPostDto;
import com.sigma.oilstation.payload.DebtUpdateDto;

import java.util.UUID;

public interface DebtService {
    ApiResponse<?> addDebt(DebtPostDto debtPostDto);

    ApiResponse<?> getByIdDebt(UUID id);

    ApiResponse<?> getAllDebtPageableWorker(Integer page, Integer size);

    ApiResponse<?> getAllDebtPageableSupplier(Integer page, Integer size);

    ApiResponse<?> getAllDebtPageableWorkerByBranch(Integer page, Integer size, UUID branchId);

    ApiResponse<?> getAllDebtPageableSupplierByBranch(Integer page, Integer size, UUID branchId);



    ApiResponse<?> getAllDebt();

    ApiResponse<?> updateDebt(UUID id, DebtUpdateDto debtUpdateDto);

    ApiResponse<?> deleteDebt(UUID id);

}
