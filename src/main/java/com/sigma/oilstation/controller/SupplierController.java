package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(SupplierController.SUPPLIER_CONTROLLER)
public interface SupplierController {

    String SUPPLIER_CONTROLLER = AppConstant.BASE_PATH + "/supplier";

    String GET_ALL_SUPPLIERS = "/all";

    String GET_LIST_OF_SUPPLIERS = "/list";

    String GET_SUPPLIER_BY_ID = "/get";

    String UPDATE_SUPPLIER_BY_ID = "/update";

    String DELETE_SUPPLIER_BY_ID = "/delete";

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping
    HttpEntity<?> addSupplier(@RequestParam(name = "name") String name);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_ALL_SUPPLIERS)
    HttpEntity<?> getAllSuppliers(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                  @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_LIST_OF_SUPPLIERS)
    HttpEntity<?> getListOfSuppliers();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_SUPPLIER_BY_ID)
    HttpEntity<?> getSupplierById(@RequestParam(name = "supplierId") Long id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping(UPDATE_SUPPLIER_BY_ID)
    HttpEntity<?> editSupplierById(@RequestParam(name = "supplierId") Long id,
                                   @RequestParam(name = "name") String name);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @DeleteMapping(DELETE_SUPPLIER_BY_ID)
    HttpEntity<?> deleteSupplierById(@RequestParam(name = "supplierId") Long id);
}
