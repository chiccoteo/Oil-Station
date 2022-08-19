package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Address;
import com.sigma.oilstation.mapper.AddressMapper;
import com.sigma.oilstation.payload.AddressDTO;
import com.sigma.oilstation.payload.AddressGetDTO;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.repository.AddressRepository;
import com.sigma.oilstation.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final AddressMapper mapper;

    @Override
    public ApiResponse<?> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> all = repository.findAll(pageable, Sort.by("createdDate").descending());

        List<AddressGetDTO> addressDTOS = mapper.toDTOList(all.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("addresses", addressDTOS);
        response.put("currentPage", all.getNumber());
        response.put("totalItems", all.getTotalElements());
        response.put("totalPages", all.getTotalPages());

        return ApiResponse.successResponse("ALL_ADDRESS_PAGES", response);
    }

    @Override
    public ApiResponse<?> getById(Long id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Address> optional = repository.findById(id);

        if (optional.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_ADDRESS_DOES_NOT_EXIST");

        return ApiResponse.successResponse("SUCCESS", mapper.toGetDTO(optional.get()));
    }

    @Override
    public ApiResponse<?> create(AddressDTO addressDTO) {
        Address address = mapper.toEntity(addressDTO);
        return ApiResponse.successResponse("SUCCESSFULLY_SAVED", mapper.toGetDTO(repository.save(address)));
    }

    @Override
    public ApiResponse<?> update(AddressDTO addressDTO, Long id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Address> optional = repository.findById(id);

        if (optional.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_ADDRESS_DOES_NOT_EXIST");

        Address address = optional.get();
        address.setRegion(addressDTO.getRegion());
        address.setDistrict(addressDTO.getDistrict());
        address.setHomeNumber(addressDTO.getHomeNumber());
        address.setStreet(addressDTO.getStreet());

        repository.save(address);

        return ApiResponse.successResponse("SUCCESSFULLY_UPDATE");
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        if (id == null)
            return ApiResponse.errorResponse("ID_MUST_NOT_BE_NULL");

        Optional<Address> optional = repository.findById(id);

        if (optional.isEmpty())
            return ApiResponse.errorResponse("SUCH_A_ADDRESS_DOES_NOT_EXIST");

        Address address = optional.get();
        repository.delete(address);

        return ApiResponse.successResponse("SUCCESSFULLY_DELETE");
    }

    @Override
    public ApiResponse<?> getAllList() {
        return ApiResponse.successResponse(mapper.toDTOList(repository.findAll()));
    }
}
