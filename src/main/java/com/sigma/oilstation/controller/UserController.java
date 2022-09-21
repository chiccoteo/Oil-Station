package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.UserDTO;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    String GET_BY_BRANCH_ID = ("/getByBranchId{id}");


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_ALL_USER_PAGEABLE)
    HttpEntity<?> getAllPageable(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                 @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_ALL_USER)
    HttpEntity<?> getAll();


    @GetMapping(GET_BY_BRANCH_ID)
    HttpEntity<?> getByBranchId(@PathVariable UUID id);


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_USER_BY_ID)
    HttpEntity<?> getById(@PathVariable UUID id);


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping(CREATE_USER)
    HttpEntity<?> create(@RequestBody UserDTO userDTO);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping(UPDATE_USER_BY_ID)
    HttpEntity<?> update(@PathVariable UUID id, @RequestBody UserDTO userDTO);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @DeleteMapping(DELETE_USER_BY_ID)
    HttpEntity<?> delete(@PathVariable UUID id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping
    HttpEntity<?> getUser();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/get/user/branch{id}")
    HttpEntity<?> getUsersByBranchId(@PathVariable UUID id);

}
