package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(SaleController.SALE_CONTROLLER)
public interface SaleController {

    String SALE_CONTROLLER = AppConstant.BASE_PATH + "/sale";

}
