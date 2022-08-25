package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.payload.UserDTO;
import com.sigma.oilstation.payload.UserGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleGetDTO", source = "role")
    @Mapping(target = "branchGetDTO", source = "branch")
    UserGetDTO toGetDTOs(User user);

    List<UserGetDTO> toGetDTOList(List<User> userList);

}
