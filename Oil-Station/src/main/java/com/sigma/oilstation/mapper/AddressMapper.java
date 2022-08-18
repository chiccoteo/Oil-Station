package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Address;
import com.sigma.oilstation.payload.AddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO toGetDTO(Address address);
    List<AddressDTO> toDTOList(List<Address> addressList);

    Address toEntity(AddressDTO addressDTO);
}
