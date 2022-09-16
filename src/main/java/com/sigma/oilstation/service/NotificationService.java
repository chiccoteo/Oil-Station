package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.NotificationPostDTO;

import java.util.UUID;

public interface NotificationService {

    ApiResponse<?> create(NotificationPostDTO notificationPostDTO);

    ApiResponse<?> update(UUID id);

    ApiResponse<?> get();

    ApiResponse<?> updateLimit(Long oilLimit);

}
