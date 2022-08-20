package com.sigma.oilstation.controller;

import com.sigma.oilstation.entity.IncomeFuel;
import com.sigma.oilstation.payload.IncomeFuelReqDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(IncomeFuelController.INCOME_FUEL_CONTROLLER)
public interface IncomeFuelController {

    String INCOME_FUEL_CONTROLLER = AppConstant.BASE_PATH + "/incomeFuel";

    @PostMapping
    HttpEntity<?> create(@RequestBody IncomeFuelReqDto incomeFuelDto);
}
