package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(SupplierController.SUPPLIER_CONTROLLER)
public interface SupplierController {

    String SUPPLIER_CONTROLLER = AppConstant.BASE_PATH + "/supplier";

}
