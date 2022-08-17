package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(IncomeFuelController.INCOME_FUEL_CONTROLLER)
public interface IncomeFuelController {

    String INCOME_FUEL_CONTROLLER = AppConstant.BASE_PATH + "/incomeFuel";

}
