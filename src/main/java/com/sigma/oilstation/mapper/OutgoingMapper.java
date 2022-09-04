package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Outgoing;
import com.sigma.oilstation.payload.OutgoingGetDTO;
import com.sigma.oilstation.payload.OutgoingGetDTOForReport;
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
    @Mapping(target = "spenderId", source = "spender.id")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy.fio")
    OutgoingGetDTO toGetDTO(Outgoing outgoing);

    @Mapping(target = "spenderFIO", source = "spender.fio")
    @Mapping(target = "spenderId", source = "spender.id")
    @Mapping(target = "category", source = "category.name")
    OutgoingGetDTOForReport toGetDTOForReport(Outgoing outgoing);

    List<OutgoingGetDTOForReport> toGetDTOListForReport(List<Outgoing> outgoingList);

    @Mapping(target = "spenderFIO", source = "spender.fio")
    @Mapping(target = "spenderId", source = "spender.id")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy.fio")
    OutgoingGetDTOWithId toGetDTOWithId(Outgoing outgoing);

    List<OutgoingGetDTOWithId> toGetDTOList(List<Outgoing> outgoingList);

}
