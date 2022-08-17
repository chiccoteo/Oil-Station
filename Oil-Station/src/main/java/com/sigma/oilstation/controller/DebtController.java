package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(DebtController.DEBT_CONTROLLER)
public interface DebtController {

    String DEBT_CONTROLLER = AppConstant.BASE_PATH + "/debt";

}
