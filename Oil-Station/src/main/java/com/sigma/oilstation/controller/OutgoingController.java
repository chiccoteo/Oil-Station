package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(OutgoingController.OUTGOING_CONTROLLER)
public interface OutgoingController {

    String OUTGOING_CONTROLLER = AppConstant.BASE_PATH + "/outgoing";

}
