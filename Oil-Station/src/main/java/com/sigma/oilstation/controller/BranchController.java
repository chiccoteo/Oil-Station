package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.BranchDTO;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(BranchController.BRANCH_CONTROLLER)
public interface BranchController {

    String BRANCH_CONTROLLER = AppConstant.BASE_PATH + "/branch";

    String GET_ALL_BRANCH_PAGEABLE = "/all-pageable";

    String GET_ALL_BRANCH = "/all";

    String GET_BRANCH_BY_ID = "/get/{id}";

    String POST_BRANCH = "/post";

    String UPDATE_BRANCH_BY_ID = "/update/{id}";

    String DELETE_BRANCH_BY_ID = "/delete/{id}";


    @GetMapping(GET_ALL_BRANCH_PAGEABLE)
    HttpEntity<?> getAllPageable(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                 @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_ALL_BRANCH)
    HttpEntity<?> getAll();

    @GetMapping(GET_BRANCH_BY_ID)
    HttpEntity<?> getById(@PathVariable UUID id);

    @PostMapping(POST_BRANCH)
    HttpEntity<?> create(@RequestBody BranchDTO branchDTO);

    @PutMapping(UPDATE_BRANCH_BY_ID)
    HttpEntity<?> edit(@PathVariable UUID id, @RequestBody BranchDTO branchDTO);

    @DeleteMapping(DELETE_BRANCH_BY_ID)
    HttpEntity<?> delete(@PathVariable UUID id);
}
