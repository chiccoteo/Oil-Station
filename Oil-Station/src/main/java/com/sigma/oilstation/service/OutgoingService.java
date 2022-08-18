package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.OutgoingPostDTO;

import java.util.UUID;

public interface OutgoingService {

    ApiResponse<?> createOutgoing(OutgoingPostDTO outgoingPostDTO);

    ApiResponse<?> getAllOutgoings(Integer page, Integer size);

    ApiResponse<?> getOutgoingById(UUID id);

    ApiResponse<?> updateOutgoingById(UUID id, OutgoingPostDTO outgoingPostDTO);

    ApiResponse<?> deleteOutgoingById(UUID id);
}
