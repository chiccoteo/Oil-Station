package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Address;
import com.sigma.oilstation.entity.Branch;
import com.sigma.oilstation.mapper.BranchMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.BranchDTO;
import com.sigma.oilstation.payload.BranchGetDTO;
import com.sigma.oilstation.repository.AddressRepository;
import com.sigma.oilstation.repository.BranchRepository;
import com.sigma.oilstation.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final AddressRepository addressRepository;
    private final BranchMapper mapper;

    @Override
    public ApiResponse<?> getAllPageable(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Branch> all = repository.findAllByDeleteIsFalse(pageable);

        List<BranchGetDTO> branchDTOList = mapper.toGetDTOList(all.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("branches", branchDTOList);
        response.put("currentPage", all.getNumber());
        response.put("totalItems", all.getTotalElements());
        response.put("totalPages", all.getTotalPages());

        return ApiResponse.successResponse("ALL_BRANCHES", response);
    }

    @Override
    public ApiResponse<?> getAll() {
        return ApiResponse.successResponse("ALL_BRANCHES", mapper.toGetDTOList(repository.findAllByDeleteIsFalse(Sort.by("createdDate").descending())));
    }

    @Override
    public ApiResponse<?> getById(UUID id) {

        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Branch> optionalBranch = repository.findById(id);

        if (optionalBranch.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_BRANCH_DOES_NOT_EXIST");

        return ApiResponse.successResponse("SUCCESS", mapper.toGetDTO(optionalBranch.get()));
    }

    @Override
    public ApiResponse<?> create(BranchDTO branchDTO) {
        if (branchDTO.getAddressId() == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Address> optionalAddress = addressRepository.findById(branchDTO.getAddressId());

        if (optionalAddress.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_ADDRESS_DOES_NOT_EXIST");


        Branch branch = mapper.toEntity(branchDTO);
        branch.setAddress(optionalAddress.get());

        return ApiResponse.successResponse("SUCCESSFULLY_SAVED", mapper.toGetDTO(repository.save(branch)));
    }


    @Override
    public ApiResponse<?> edit(UUID id, BranchDTO branchDTO) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Branch> optionalBranch = repository.findById(id);

        if (optionalBranch.isEmpty()) {
            return ApiResponse.errorResponse("SUCH_A_BRANCH_OR_ADDRESS_DOES_NOT_EXIST");
        }
        Branch branch = optionalBranch.get();

        Optional<Address> optionalAddress = addressRepository.findById(branchDTO.getAddressId());
        if (optionalAddress.isEmpty()) {
            return ApiResponse.errorResponse("SUCH_A_BRANCH_OR_ADDRESS_DOES_NOT_EXIST");
        }

        branch.setAddress(optionalAddress.get());
        branch.setName(branchDTO.getName());
        repository.save(branch);
        return ApiResponse.successResponse("SUCCESSFULLY_UPDATE");
    }


    @Override
    public ApiResponse<?> delete(UUID id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Branch> optionalBranch = repository.findById(id);

        if (optionalBranch.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_BRANCH_DOES_NOT_EXIST");

        Branch branch = optionalBranch.get();
        branch.setDelete(true);
        repository.save(branch);

        return ApiResponse.successResponse("SUCCESSFULLY_DELETED");
    }
}
