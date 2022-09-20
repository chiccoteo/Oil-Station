package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.DebtPostDto;
import com.sigma.oilstation.payload.IncomeFuelPostDto;
import com.sigma.oilstation.payload.IncomeFuelDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(IncomeFuelController.INCOME_FUEL_CONTROLLER)
public interface IncomeFuelController {

    String INCOME_FUEL_CONTROLLER = AppConstant.BASE_PATH + "/incomeFuel";

    String GET_ALL_BY_PAGE = AppConstant.BASE_PATH + "/page";

    String GET_DAILY_INCOME_FUEL = "/daily";

    String GET_WEEKLY_INCOME_FUEL = "/weekly";

    String GET_MONTHLY_INCOME_FUEL = "/monthly";

    String GET_ANNUAL_INCOME_FUEL = "/annually";

    String GET_BETWEEN_INCOME_FUEL = "/between";

    String PUT_INCOME_FUEL = "/{id}";


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @PostMapping
    HttpEntity<?> create(@RequestBody IncomeFuelPostDto incomeFuelDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @PutMapping(PUT_INCOME_FUEL)
    HttpEntity<?> edit(@PathVariable UUID id, @RequestBody IncomeFuelDto incomeFuelDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @DeleteMapping("/{id}")
    HttpEntity<?> delete(@PathVariable UUID id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping("/{id}")
    HttpEntity<?> getById(@PathVariable UUID id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping
    HttpEntity<?> get();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ALL_BY_PAGE)
    HttpEntity<?> getPage(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_DAILY_INCOME_FUEL)
    HttpEntity<?> getDailyIncomeFuel(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_WEEKLY_INCOME_FUEL)
    HttpEntity<?> getWeeklyIncomeFuel(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_MONTHLY_INCOME_FUEL)
    HttpEntity<?> getMonthlyIncomeFuel(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                       @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ANNUAL_INCOME_FUEL)
    HttpEntity<?> getAnnualIncomeFuel(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_BETWEEN_INCOME_FUEL)
    HttpEntity<?> getInterimIncomeFuel(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping("/byBranch/{branchId}")
    HttpEntity<?> getByBranchId(@PathVariable UUID branchId,
                                @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_DAILY_INCOME_FUEL + "/{branchId}")
    HttpEntity<?> getDailyBranchIncomeFuel(@PathVariable UUID branchId,
                                           @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                           @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_WEEKLY_INCOME_FUEL + "/{branchId}")
    HttpEntity<?> getWeeklyBranchIncomeFuel(@PathVariable UUID branchId,
                                            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_MONTHLY_INCOME_FUEL + "/{branchId}")
    HttpEntity<?> getMonthlyBranchIncomeFuel(@PathVariable UUID branchId,
                                             @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                             @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ANNUAL_INCOME_FUEL + "/{branchId}")
    HttpEntity<?> getAnnualBranchIncomeFuel(@PathVariable UUID branchId,
                                      @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_BETWEEN_INCOME_FUEL + "/{branchId}")
    HttpEntity<?> getInterimBranchIncomeFuel(
            @PathVariable UUID branchId,
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);

}
