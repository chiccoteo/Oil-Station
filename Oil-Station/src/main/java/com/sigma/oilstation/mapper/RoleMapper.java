package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Role;
import com.sigma.oilstation.payload.RoleGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "roleType", source = "type")
    RoleGetDTO toGetDTO(Role role);

    List<RoleGetDTO> toGetDTOList(List<Role> roleList);

}
