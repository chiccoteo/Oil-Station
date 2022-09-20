package com.sigma.oilstation.service;


import com.sigma.oilstation.payload.ApiResponse;

public interface LimitService {

    ApiResponse<?> update(Long limit);

    ApiResponse<?> get();

}
