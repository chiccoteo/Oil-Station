package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {

    String USER_CONTROLLER = AppConstant.BASE_PATH + "/user";

}
