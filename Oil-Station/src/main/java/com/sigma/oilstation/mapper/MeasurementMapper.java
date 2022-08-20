package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Measurement;
import com.sigma.oilstation.payload.MeasurementGetDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
    MeasurementGetDto toGetDto(Measurement measurement);
    List<MeasurementGetDto> toDoList(List<Measurement> measurementList);
    Measurement toEntity(String measurementDto);
}
