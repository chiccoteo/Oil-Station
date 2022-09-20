package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.NotificationPostDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.*;

@RequestMapping(BASE_PATH + NotificationController.NOTIFICATION_CONTROLLER)
public interface NotificationController {

    String NOTIFICATION_CONTROLLER = "/notification";

    String CREATE_NOT = "/create";

    String UPDATE = "/update";

    String GET = "/get";

    String GET_ALL = "/all";

    @PostMapping(CREATE_NOT)
    HttpEntity<?> create(@RequestBody NotificationPostDTO notificationPostDTO);

    @GetMapping(GET)
    HttpEntity<?> get();

    @GetMapping(GET_ALL)
    HttpEntity<?> getAll(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                         @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PutMapping(UPDATE)
    HttpEntity<?> update(@RequestParam(name = "id") UUID id);

}
