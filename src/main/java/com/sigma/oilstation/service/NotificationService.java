package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.NotificationPostDTO;

import java.util.UUID;

public interface NotificationService {

    ApiResponse<?> create(NotificationPostDTO notificationPostDTO);

    ApiResponse<?> get();

    ApiResponse<?> getAll(Integer page, Integer size);

    ApiResponse<?> update(UUID id);

}
