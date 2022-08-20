package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(FuelReportController.FUEL_REPORT_CONTROLLER)
public interface FuelReportController {

    String FUEL_REPORT_CONTROLLER = AppConstant.BASE_PATH + "/fuelReport";

    @PostMapping
    HttpEntity<?> create(@RequestBody FuelReportPostDto fuelPostDto);

    @PutMapping
    HttpEntity<?> edit(@RequestBody FuelReportDto fuelReportDto);

    @GetMapping("/{id}")
    HttpEntity<?> getById(@PathVariable UUID id);

    @GetMapping
    HttpEntity<?> get();

    @GetMapping("/page")
    HttpEntity<?> getPage(@RequestParam int page,@RequestParam int size);

    @GetMapping("/{branchId}")
    HttpEntity<?> getByBranch(@PathVariable UUID branchId);

}
