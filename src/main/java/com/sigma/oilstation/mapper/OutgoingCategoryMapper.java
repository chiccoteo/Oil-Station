package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.OutgoingCategory;
import com.sigma.oilstation.payload.OutgoingCategoryGetDTO;
import com.sigma.oilstation.payload.OutgoingCategoryGetDTOWithId;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OutgoingCategoryMapper {

    OutgoingCategoryGetDTO toGetDTO(OutgoingCategory outgoingCategory);

    OutgoingCategoryGetDTOWithId toGetDTOWithId(OutgoingCategory outgoingCategory);

    List<OutgoingCategoryGetDTOWithId> toGetDTOList(List<OutgoingCategory> outgoingCategoryList);

}
