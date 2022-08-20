package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.RoleDTO;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static com.sigma.oilstation.utils.AppConstant.*;

@RequestMapping(RoleController.ROLE_CONTROLLER)
public interface RoleController {
    String ROLE_CONTROLLER = AppConstant.BASE_PATH + "/role";

    String GET_ALL_PAGEABLE = "/all-pageable";
    String GET_ALL_ROLE = "/all";
    String GET_ROLE_BY_ID = "/get{id}";
    String CREATE_ROLE = "/post";
    String UPDATE_ROLE_BY_ID = "/update{id}";
    String DELETE_ROLE_BY_ID = "/delete{id}";

    @GetMapping(GET_ALL_PAGEABLE)
    HttpEntity<?> getAllPageable(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                 @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_ALL_ROLE)
    HttpEntity<?> getAll();

    @GetMapping(GET_ROLE_BY_ID)
    HttpEntity<?> getById(@PathVariable Long id);

    @PostMapping(CREATE_ROLE)
    HttpEntity<?> create(@RequestBody RoleDTO roleDTO);

    @PutMapping(UPDATE_ROLE_BY_ID)
    HttpEntity<?> update(@PathVariable Long id, @RequestBody RoleDTO roleDTO);

    @DeleteMapping(DELETE_ROLE_BY_ID)
    HttpEntity<?> delete(@PathVariable Long id);

}
