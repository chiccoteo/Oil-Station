package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.mapper.UserMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.UserDTO;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public ApiResponse<?> getAllUserPageable(Integer page, Integer size) {

        return null;
    }

    @Override
    public ApiResponse<?> getAllUser() {
        return null;
    }

    @Override
    public ApiResponse<?> getByIdUser(UUID id) {
        return null;
    }

    @Override
    public ApiResponse<?> createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ApiResponse<?> updateUser(UUID id, UserDTO userDTO) {
        return null;
    }

    @Override
    public ApiResponse<?> deleteUser(UUID id) {
        return null;
    }
}
