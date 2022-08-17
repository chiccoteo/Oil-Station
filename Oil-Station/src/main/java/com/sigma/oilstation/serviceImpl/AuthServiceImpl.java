package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.LoginDTO;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.secret.JwtProvider;
import com.sigma.oilstation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager manager;

    @Override
    public ApiResponse<?> login(LoginDTO loginDTO) {
//        Optional<User> optionalUser = userRepo.findByUsername(loginDTO.getUsername());
//        if (optionalUser.isEmpty())
//            return ApiResponse.errorResponse("User not found");
//        User user = optionalUser.get();
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        User principal = (User) authenticate.getPrincipal();
//        System.out.println(passwordEncoder.encode(loginDTO.getPassword()));
//        System.out.println(user.getPassword());
//        if (user.getPassword().equals(passwordEncoder.encode(loginDTO.getPassword())))
            return ApiResponse.successResponse("You have successfully logged in", jwtProvider.generateToken(principal));
//        else
//            return ApiResponse.errorResponse("No");
    }
}
