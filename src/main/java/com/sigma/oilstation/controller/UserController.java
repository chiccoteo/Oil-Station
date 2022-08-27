package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.UserDTO;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {

    String USER_CONTROLLER = AppConstant.BASE_PATH + "/user";

    String GET_ALL_USER_PAGEABLE = "/all-pageable";

    String GET_ALL_USER = "/all";

    String GET_USER_BY_ID = "/get{id}";

    String CREATE_USER = "/post";

    String UPDATE_USER_BY_ID = "/update{id}";

    String DELETE_USER_BY_ID = "/delete{id}";

    @GetMapping(GET_ALL_USER_PAGEABLE)
    HttpEntity<?> getAllPageable(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                 @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_ALL_USER)
    HttpEntity<?> getAll();

    @GetMapping(GET_USER_BY_ID)
    HttpEntity<?> getById(@PathVariable UUID id);

    @PostMapping(CREATE_USER)
    HttpEntity<?> create(@RequestBody UserDTO userDTO);

    @PutMapping(UPDATE_USER_BY_ID)
    HttpEntity<?> update(@PathVariable UUID id, @RequestBody UserDTO userDTO);

    @DeleteMapping(DELETE_USER_BY_ID)
    HttpEntity<?> delete(@PathVariable UUID id);

    @GetMapping
    HttpEntity<?> getUser();

}
