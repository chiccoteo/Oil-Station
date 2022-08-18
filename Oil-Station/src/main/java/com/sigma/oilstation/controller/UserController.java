package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {

    String USER_CONTROLLER = AppConstant.BASE_PATH + "/user";

    String GET_ALL_USER = "/all";

    String GET_USER_BY_ID = "/get";

    String UPDATE_USER_BY_ID = "/update";

    String DELETE_USER_BY_ID = "/delete";

}
