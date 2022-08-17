package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(FuelReportController.FUEL_REPORT_CONTROLLER)
public interface FuelReportController {

    String FUEL_REPORT_CONTROLLER = AppConstant.BASE_PATH + "/fuelReport";

}
