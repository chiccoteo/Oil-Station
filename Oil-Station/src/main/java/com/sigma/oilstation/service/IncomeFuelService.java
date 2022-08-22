package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.IncomeFuelPostDto;
import com.sigma.oilstation.payload.IncomeFuelDto;

import java.util.Date;
import java.util.UUID;

public interface IncomeFuelService {
    ApiResponse<?> create(IncomeFuelPostDto incomeFuelDto);

    ApiResponse<?> edit(IncomeFuelDto incomeFuelDto);

    ApiResponse<?> getById(UUID id);

    ApiResponse<?> get();
    ApiResponse<?> get(int page,int size);

    ApiResponse<?> delete(UUID id);

    ApiResponse<?> getDailyIncomeFuel(int page, int size);

    ApiResponse<?> getWeeklyIncomeFuel(int page, int size);

    ApiResponse<?> getMonthlyIncomeFuel(int page, int size);

    ApiResponse<?> getAnnuallyIncomeFuel(int page, int size);

    ApiResponse<?> getInterimIncomeFuel(int page, int size, Date startDate, Date endDate);
}
