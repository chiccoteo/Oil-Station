package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.NotificationPostDTO;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.*;

@RequestMapping(BASE_PATH + NotificationController.NOTIFICATION_CONTROLLER)
public interface NotificationController {

    String NOTIFICATION_CONTROLLER = "/notification";

    String CREATE_NOT = "/create";

    String UPDATE = "/update";

    String UPDATE_LIMIT = "/updateLimit";

    String GET = "/get";

    String GET_ALL = "/all";

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(CREATE_NOT)
    HttpEntity<?> create(@RequestBody NotificationPostDTO notificationPostDTO);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET)
    HttpEntity<?> get();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET_ALL)
    HttpEntity<?> getAll(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                         @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET + "/oil/limit")
    HttpEntity<?> getOilLimit();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(UPDATE_LIMIT)
    HttpEntity<?> updateLimit(@RequestParam(name = "limit") Long oilLimit);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(UPDATE)
    HttpEntity<?> update(@RequestParam(name = "id") UUID id);

}
