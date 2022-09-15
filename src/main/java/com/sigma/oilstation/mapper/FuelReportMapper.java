package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.FuelReport;
import com.sigma.oilstation.payload.FuelReportCurrent;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface FuelReportMapper {

    @Mapping(target = "fuel", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "amountAtEndOfShift", ignore = true)
    @Mapping(target = "salePrice", ignore = true)
    FuelReport toEntity(FuelReportPostDto fuelReportPostDto);

    @Mapping(target = "fuel", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "salePrice", ignore = true)
    FuelReport toEntity(FuelReportDto fuelReportDto);

    @Mapping(target = "fuelId", source = "fuel.id")
    @Mapping(target = "employeeId", source = "employee.id")
    FuelReportDto toDto(FuelReport fuelReport);

    @Mapping(target = "fuelId", source = "fuel.id")
    @Mapping(target = "branchId", source = "employee.branch.id")
    @Mapping(target = "amount", source = "amountAtStartOfShift")
    @Mapping(target = "measurementId", source = "fuel.outcomeMeasurement.id")
    FuelReportCurrent toCurrentFuelReport(FuelReport fuelReport);

    List<FuelReportCurrent> toCurrentFuelReportList(List<FuelReport> fuelReportList);

}
