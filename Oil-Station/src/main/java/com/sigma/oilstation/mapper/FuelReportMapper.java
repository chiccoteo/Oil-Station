package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.FuelReport;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface FuelReportMapper {

    @Mapping(target = "fuel",ignore = true)
    @Mapping(target = "employee",ignore = true)
    @Mapping(target = "amountAtEndOfShift",ignore = true)
    FuelReport toEntity(FuelReportPostDto fuelReportPostDto);

    @Mapping(target = "fuel",ignore = true)
    @Mapping(target = "employee",ignore = true)
    FuelReport toEntity(FuelReportDto fuelReportDto);

    @Mapping(target = "fuelId", source = "fuel.id")
    @Mapping(target = "employeeId", source = "employee.id")
    FuelReportDto toDto(FuelReport fuelReport);
}
