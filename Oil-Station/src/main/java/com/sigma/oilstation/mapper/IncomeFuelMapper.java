package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.IncomeFuel;
import com.sigma.oilstation.payload.IncomeFuelPostDto;
import com.sigma.oilstation.payload.IncomeFuelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncomeFuelMapper {

    @Mapping(target = "fuel",ignore = true)
    @Mapping(target = "employee",ignore = true)
    IncomeFuel toEntity(IncomeFuelPostDto incomeFuelDto);

    @Mapping(target = "fuel",ignore = true)
    @Mapping(target = "employee",ignore = true)
    IncomeFuel toEntity(IncomeFuelDto incomeFuelDto);

    @Mapping(target = "fuelId", source = "fuel.id")
    @Mapping(target = "employeeId", source = "employee.id")
    IncomeFuelDto toDto(IncomeFuel incomeFuel);

}
