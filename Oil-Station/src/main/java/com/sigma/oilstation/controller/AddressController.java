package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AddressController.ADDRESS_CONTROLLER)
public interface AddressController {

    String ADDRESS_CONTROLLER = AppConstant.BASE_PATH + "/address";

}
