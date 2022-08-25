package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(FuelReportController.FUEL_REPORT_CONTROLLER)
public interface FuelReportController {

    String FUEL_REPORT_CONTROLLER = AppConstant.BASE_PATH + "/fuelReport";

    String GET_ALL_BY_PAGE = AppConstant.BASE_PATH + "/page";

    String GET_DAILY_FUEL_REPORT= "/daily";

    String GET_WEEKLY_FUEL_REPORT= "/weekly";

    String GET_MONTHLY_FUEL_REPORT= "/monthly";

    String GET_ANNUAL_FUEL_REPORT= "/annually";

    String GET_BETWEEN_FUEL_REPORT= "/between";

    @PostMapping
    HttpEntity<?> create(@RequestBody FuelReportPostDto fuelPostDto);

    @PutMapping
    HttpEntity<?> edit(@RequestBody FuelReportDto fuelReportDto);

    @GetMapping("/{id}")
    HttpEntity<?> getById(@PathVariable UUID id);

    @GetMapping
    HttpEntity<?> get();

    @GetMapping(GET_ALL_BY_PAGE)
    HttpEntity<?> getPage(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                          @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @GetMapping("/{branchId}")
    HttpEntity<?> getByBranch(@PathVariable UUID branchId);

    @GetMapping(GET_DAILY_FUEL_REPORT)
    HttpEntity<?> getDailyFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                    @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @GetMapping(GET_WEEKLY_FUEL_REPORT)
    HttpEntity<?> getWeeklyFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @GetMapping(GET_MONTHLY_FUEL_REPORT)
    HttpEntity<?> getMonthlyFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @GetMapping(GET_ANNUAL_FUEL_REPORT)
    HttpEntity<?> getAnnualFuelReport(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size);

    @GetMapping(GET_BETWEEN_FUEL_REPORT)
    HttpEntity<?> getInterimFuelReport(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);


}