package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Branch;
import com.sigma.oilstation.entity.Role;
import com.sigma.oilstation.enums.RoleType;
import com.sigma.oilstation.mapper.RoleMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.RoleDTO;
import com.sigma.oilstation.payload.RoleGetDTO;
import com.sigma.oilstation.repository.RoleRepository;
import com.sigma.oilstation.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public ApiResponse<?> getAllPageable(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by("createdDate").descending());
        List<RoleGetDTO> roleGetDTOS = mapper.toGetDTOList(repository.findAll(pageable).toList());
        return ApiResponse.successResponse("ALL_ROLES", roleGetDTOS);
    }

    @Override
    public ApiResponse<?> getAll() {
        return ApiResponse.successResponse("ALL_ROLES", mapper.toGetDTOList(repository.findAll(Sort.by("createdDate").descending())));
    }

    @Override
    public ApiResponse<?> getById(Long id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Role> optionalRole = repository.findById(id);

        if (optionalRole.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_ROLE_DOES_NOT_EXIST");

        return ApiResponse.successResponse("SUCCESS", mapper.toGetDTO(optionalRole.get()));
    }

    @Override
    public ApiResponse<?> create(RoleDTO roleDTO) {
        if (!(repository.existsByName(roleDTO.getName()))) {
            Role role = new Role();
            role.setName(roleDTO.getName());
            role.setType(RoleType.ROLE_EMPLOYEE);
            return ApiResponse.successResponse("SUCCESSFULLY_CREATE_ROLE", mapper.toGetDTO(repository.save(role)));
        }
        return ApiResponse.errorResponse("SUCH_A_ROLE_EXIST");
    }

    @Override
    public ApiResponse<?> update(Long id, RoleDTO roleDTO) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        if (!(repository.existsByName(roleDTO.getName()))) {
            Optional<Role> optionalRole = repository.findById(id);

            if (optionalRole.isEmpty())
                return ApiResponse.errorResponse("SUCH_A_ROLE_DOES_NOT_EXIST");

            Role role = optionalRole.get();

            if ((!roleDTO.getRoleType().equals(RoleType.ROLE_ADMIN)) && (role.getType().equals(RoleType.ROLE_EMPLOYEE) || role.getType().equals(RoleType.ROLE_MANAGER))) {
                role.setName(roleDTO.getName());
                role.setType(roleDTO.getRoleType());
                repository.save(role);
                return ApiResponse.successResponse("SUCCESSFULLY_UPDATE_ROLE");
            }
            return ApiResponse.errorResponse("ROLE_CHANGE_RESTRICTION");
        }
        return ApiResponse.errorResponse("SUCH_A_ROLE_EXIST");
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Role> optionalRole = repository.findById(id);

        if (optionalRole.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_ROLE_DOES_NOT_EXIST");

        Role role = optionalRole.get();

        if (role.getType().equals(RoleType.ROLE_EMPLOYEE) || role.getType().equals(RoleType.ROLE_MANAGER)) {
            repository.delete(role);
            return ApiResponse.successResponse("SUCCESSFULLY_DELETE_ROLE");
        }
        return ApiResponse.errorResponse("ROLE_CHANGE_RESTRICTION");
    }
}
