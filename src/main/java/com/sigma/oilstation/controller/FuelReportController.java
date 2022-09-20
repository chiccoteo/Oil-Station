package com.sigma.oilstation.controller;

import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(FuelReportController.FUEL_REPORT_CONTROLLER)
public interface FuelReportController {

    String FUEL_REPORT_CONTROLLER = AppConstant.BASE_PATH + "/fuelReport";

    String GET_ALL_BY_PAGE = AppConstant.BASE_PATH + "/page";

    String GET_DAILY_FUEL_REPORT = "/daily";

    String GET_WEEKLY_FUEL_REPORT = "/weekly";

    String GET_MONTHLY_FUEL_REPORT = "/monthly";

    String GET_ANNUAL_FUEL_REPORT = "/annually";

    String GET_BETWEEN_FUEL_REPORT = "/between";

    String GET_FUEL_REPORT_CURRENT = "/current";

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping
    HttpEntity<?> create(@RequestBody @Valid FuelReportPostDto fuelPostDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping
    HttpEntity<?> edit(@RequestBody FuelReportDto fuelReportDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/{id}")
    HttpEntity<?> getById(@PathVariable UUID id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping
    HttpEntity<?> get();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_ALL_BY_PAGE)
    HttpEntity<?> getPage(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/byBranch/{branchId}")
    HttpEntity<?> getByBranch(@PathVariable UUID branchId,
                              @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size) throws PageSizeException;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_DAILY_FUEL_REPORT + "/{branchId}")
    HttpEntity<?> getDailyBranchFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                           @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size, @PathVariable UUID branchId);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_WEEKLY_FUEL_REPORT + "/{branchId}")
    HttpEntity<?> getWeeklyBranchFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size, @PathVariable UUID branchId);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_MONTHLY_FUEL_REPORT + "/{branchId}")
    HttpEntity<?> getMonthlyBranchFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                             @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size, @PathVariable UUID branchId);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_ANNUAL_FUEL_REPORT + "/{branchId}")
    HttpEntity<?> getAnnualBranchFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size, @PathVariable UUID branchId);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_BETWEEN_FUEL_REPORT + "/{branchId}")
    HttpEntity<?> getInterimBranchFuelReport(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate, @PathVariable UUID branchId);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_DAILY_FUEL_REPORT)
    HttpEntity<?> getDailyFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_WEEKLY_FUEL_REPORT)
    HttpEntity<?> getWeeklyFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_MONTHLY_FUEL_REPORT)
    HttpEntity<?> getMonthlyFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                       @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_ANNUAL_FUEL_REPORT)
    HttpEntity<?> getAnnualFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping(GET_BETWEEN_FUEL_REPORT)
    HttpEntity<?> getInterimFuelReport(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_FUEL_REPORT_CURRENT)
    HttpEntity<?> getFuelReportCurrent();

}
