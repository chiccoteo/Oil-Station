package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(MeasurementController.MEASUREMENT_CONTROLLER)
public interface MeasurementController {

    String MEASUREMENT_CONTROLLER = AppConstant.BASE_PATH + "/measurement";

}
