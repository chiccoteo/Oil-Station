package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.LoginDTO;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AuthController.AUTH_CONTROLLER)
public interface AuthController {

    String AUTH_CONTROLLER = AppConstant.BASE_PATH+"/auth";

    String LOGIN = "/login";

    @PostMapping(LOGIN)
    HttpEntity<?> login(@RequestBody LoginDTO loginDTO);


}
