package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Address;
import com.sigma.oilstation.payload.AddressDTO;
import com.sigma.oilstation.payload.AddressGetDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressGetDTO toGetDTO(Address address);
    List<AddressGetDTO> toDTOList(List<Address> addressList);

    Address toEntity(AddressDTO addressDTO);
}
