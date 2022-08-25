package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Branch;
import com.sigma.oilstation.entity.Role;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.mapper.UserMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.UserDTO;
import com.sigma.oilstation.payload.UserGetDTO;
import com.sigma.oilstation.repository.BranchRepository;
import com.sigma.oilstation.repository.RoleRepository;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;
    private final BranchRepository branchRepository;

    @Override
    public ApiResponse<?> getAllUserPageable(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<User> all = repository.findAll(pageable);
        List<UserGetDTO> userGetDTOList = mapper.toGetDTOList(repository.findAll(pageable).toList());

        Map<String, Object> response = new HashMap<>();

        response.put("branches", userGetDTOList);
        response.put("currentPage", all.getNumber());
        response.put("totalItems", all.getTotalElements());
        response.put("totalPages", all.getTotalPages());

        return ApiResponse.successResponse("ALL_USERS", response);
    }

    @Override
    public ApiResponse<?> getAllUser() {
        return ApiResponse.successResponse("ALL_USERS", mapper.toGetDTOList(repository.findAll(Sort.by("createdDate").descending())));
    }

    @Override
    public ApiResponse<?> getByIdUser(UUID id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_USER_DOES_NOT_EXIST");

        return ApiResponse.successResponse("SUCCESS", mapper.toGetDTOs(optionalUser.get()));
    }

    @Override
    public ApiResponse<?> createUser(UserDTO userDTO) {
        if (!(repository.existsByUsername(userDTO.getUsername()))) {
            User user = new User();
            Branch branch = new Branch();
            Role role = new Role();

            Optional<Role> optionalRole = roleRepository.findById(userDTO.getRoleId());
            Optional<Branch> optionalBranch = branchRepository.findById(userDTO.getBranchId());


            if (optionalBranch.isPresent() && optionalRole.isPresent()) {
                branch = optionalBranch.get();
                role = optionalRole.get();
            } else {
                return ApiResponse.errorResponse("SUCH_A_ROLE_OR_BRANCH_DOES_NOT_EXIST");
            }
            user.setFio(userDTO.getFio());
            user.setUsername(userDTO.getUsername());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setPassword(userDTO.getPassword());
            user.setBranch(branch);
            user.setRole(role);
            user.setBlock(false);
            user.setDeleted(false);
            return ApiResponse.successResponse("SUCCESSFULLY_CREATE", mapper.toGetDTOs(repository.save(user)));
        }
        return ApiResponse.errorResponse("SUCH_A_USER_EXIST");
    }

    @Override
    public ApiResponse<?> updateUser(UUID id, UserDTO userDTO) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_USER_DOES_NOT_EXIST");

        User user = optionalUser.get();

        Optional<Role> optionalRole = roleRepository.findById(userDTO.getRoleId());
        Optional<Branch> optionalBranch = branchRepository.findById(userDTO.getBranchId());

        Branch branch = new Branch();
        Role role = new Role();

        if (optionalBranch.isPresent() && optionalRole.isPresent()) {
            branch = optionalBranch.get();
            role = optionalRole.get();
        } else {
            return ApiResponse.errorResponse("SUCH_A_ROLE_OR_BRANCH_DOES_NOT_EXIST");
        }

        if (repository.existsByUsername(userDTO.getUsername())) {
            if (!user.getUsername().equals(userDTO.getUsername())) {
                return ApiResponse.errorResponse("ERROR_USERNAME");
            }
        }
        user.setUsername(userDTO.getUsername());
        user.setFio(userDTO.getFio());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBranch(branch);
        user.setRole(role);
        user.setBlock(false);
        user.setDeleted(false);
        return ApiResponse.successResponse("SUCCESSFULLY_CREATE", mapper.toGetDTOs(repository.save(user)));
    }

    @Override
    public ApiResponse<?> deleteUser(UUID id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_USER_DOES_NOT_EXIST");

        User user = optionalUser.get();
        user.setDeleted(true);
        return ApiResponse.successResponse("SUCCESSFULLY_DELETED");
    }
}