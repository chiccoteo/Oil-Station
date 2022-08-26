package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.AuthGetDTO;
import com.sigma.oilstation.payload.LoginDTO;
import com.sigma.oilstation.secret.JwtProvider;
import com.sigma.oilstation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;

    private final AuthenticationManager manager;

    @Override
    public ApiResponse<?> login(LoginDTO loginDTO) {
        try {
            Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            User principal = (User) authenticate.getPrincipal();
            AuthGetDTO authGetDTO = new AuthGetDTO();
            authGetDTO.setToken(jwtProvider.generateToken(principal));
            authGetDTO.setRoleType(principal.getRole().getType());
            authGetDTO.setBlocked(principal.isBlock());
            return ApiResponse.successResponse("You have successfully logged in", authGetDTO);
        } catch (Exception e) {
            return ApiResponse.errorResponse("Username or password is incorrect");
        }
    }
}
