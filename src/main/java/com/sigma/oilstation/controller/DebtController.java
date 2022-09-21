package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.DebtPostDto;
import com.sigma.oilstation.payload.DebtUpdateDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.*;

@RequestMapping(DebtController.DEBT_CONTROLLER)
public interface DebtController {

    String DEBT_CONTROLLER = AppConstant.BASE_PATH + "/debt";

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @PostMapping(POST_PATH)
    HttpEntity<?> addDebt(@RequestBody DebtPostDto debtPostDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_BY_ID_PATH)
    HttpEntity<?> getByIdDebt(@PathVariable UUID id);


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ALL_PAGEABLE_PATH+"/worker")
    HttpEntity<?> getAllDebtPageableWorker(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ALL_PAGEABLE_PATH+"/supplier")
    HttpEntity<?> getAllDebtPageableSupplier(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ALL_PAGEABLE_PATH+"/worker/{id}")
    HttpEntity<?> getAllDebtPageableWorkerByBranch(@PathVariable UUID id,
                                                   @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                                   @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ALL_PAGEABLE_PATH+"/supplier/{id}")
    HttpEntity<?> getAllDebtPageableSupplierByBranch(@PathVariable UUID id,
                                                   @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                                   @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET_ALL_PATH)
    HttpEntity<?> getAllDebt();


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @PutMapping(PUT_PATH)
    HttpEntity<?> updateDebt(@PathVariable UUID id, @RequestBody DebtUpdateDto debtUpdateDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @DeleteMapping(DELETE_PATH)
    HttpEntity<?> deleteDebt(@PathVariable UUID id);

}
