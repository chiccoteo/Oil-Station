package com.sigma.oilstation.service;

import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.LoginDTO;

public interface AuthService {

    ApiResponse<?> login(LoginDTO loginDTO);

}
