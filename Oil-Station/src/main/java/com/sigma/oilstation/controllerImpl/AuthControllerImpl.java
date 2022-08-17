package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.AuthController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.LoginDTO;
import com.sigma.oilstation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public HttpEntity<?> login(LoginDTO loginDTO) {
        ApiResponse<?> apiResponse = authService.login(loginDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
