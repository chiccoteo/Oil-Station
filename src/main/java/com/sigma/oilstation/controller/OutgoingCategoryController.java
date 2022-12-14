package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(OutgoingCategoryController.OUTGOING_CATEGORY_CONTROLLER)
public interface OutgoingCategoryController {

    String OUTGOING_CATEGORY_CONTROLLER = AppConstant.BASE_PATH + "/outgoingCategory";

    String GET_ALL_OUTGOING_CATEGORIES = "/all";

    String GET_LIST_OF_OUTGOING_CATEGORIES = "/list";

    String GET_OUTGOING_CATEGORY_BY_ID = "/get";

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET_ALL_OUTGOING_CATEGORIES)
    HttpEntity<?> getAllOutgoingCategories(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                           @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET_LIST_OF_OUTGOING_CATEGORIES)
    HttpEntity<?> getListOfOutgoingCategories();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET_OUTGOING_CATEGORY_BY_ID)
    HttpEntity<?> getOutgoingCategoryById(@RequestParam(name = "outgoingCategoryId") UUID id);

}
