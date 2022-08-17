package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(FuelController.FUEL_CONTROLLER)
public interface FuelController {

    String FUEL_CONTROLLER = AppConstant.BASE_PATH + "/fuel";

}
