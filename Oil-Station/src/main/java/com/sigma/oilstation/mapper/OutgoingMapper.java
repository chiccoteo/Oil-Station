package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Outgoing;
import com.sigma.oilstation.payload.OutgoingGetDTO;
import com.sigma.oilstation.payload.OutgoingGetDTOWithId;
import com.sigma.oilstation.payload.OutgoingPostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OutgoingMapper {

    @Mapping(target = "spender", ignore = true)
    @Mapping(target = "category.name", source = "outgoingCategoryName")
    Outgoing toEntity(OutgoingPostDTO outgoingPostDTO);

    @Mapping(target = "spenderFIO", source = "spender.fio")
    @Mapping(target = "category", source = "category.name")
    OutgoingGetDTO toGetDTO(Outgoing outgoing);

    @Mapping(target = "spenderFIO", source = "spender.fio")
    @Mapping(target = "category", source = "category.name")
    OutgoingGetDTOWithId toGetDTOWithId(Outgoing outgoing);

    List<OutgoingGetDTOWithId> toGetDTOList(List<Outgoing> outgoingList);

}
