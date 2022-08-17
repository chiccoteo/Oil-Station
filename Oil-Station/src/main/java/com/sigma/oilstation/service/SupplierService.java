package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;

public interface SupplierService {

    ApiResponse<?> addSupplier(String name);

    ApiResponse<?> getAllSuppliers(Integer page, Integer size);

    ApiResponse<?> getSupplierById(Long id);

    ApiResponse<?> editSupplierById(Long id, String name);

    ApiResponse<?> deleteSupplierById(Long id);
}
