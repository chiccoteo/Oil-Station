package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.OutgoingPostDTO;

import java.sql.Timestamp;
import java.util.UUID;

public interface OutgoingService {

    ApiResponse<?> createOutgoing(OutgoingPostDTO outgoingPostDTO);

    ApiResponse<?> getAllOutgoings(Integer page, Integer size);

    ApiResponse<?> getOutgoingById(UUID id);

    ApiResponse<?> updateOutgoingById(UUID id, OutgoingPostDTO outgoingPostDTO);

    ApiResponse<?> deleteOutgoingById(UUID id);

    ApiResponse<?> getListOfOutgoings();

    ApiResponse<?> getInterimOutgoings(Timestamp startTimestamp, Timestamp endTimestamp, Integer page, Integer size);

    ApiResponse<?> getDailyOutgoings(Integer page, Integer size);

    ApiResponse<?> getWeeklyOutgoings(Integer page, Integer size);

    ApiResponse<?> getAnnualOutgoings(Integer page, Integer size);

    ApiResponse<?> getMonthlyOutgoings(Integer page, Integer size);

}
