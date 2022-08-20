package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Branch;
import com.sigma.oilstation.payload.BranchDTO;
import com.sigma.oilstation.payload.BranchGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "addressDTO", source = "address")
    BranchGetDTO toGetDTO(Branch branch);

    List<BranchGetDTO> toGetDTOList(List<Branch> branchList);



    @Mapping(target = "address.id", source = "addressId")
    Branch toEntity(BranchDTO branchDTO);

}
