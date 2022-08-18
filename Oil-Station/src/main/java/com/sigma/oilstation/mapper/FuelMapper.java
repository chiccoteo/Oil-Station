package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Fuel;
import com.sigma.oilstation.payload.FuelGetDto;
import com.sigma.oilstation.payload.FuelPostDto;
import com.sigma.oilstation.payload.FuelUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FuelMapper {

    Fuel postDtoToEntity(FuelPostDto fuelPostDto);

    FuelGetDto entityToGetDto(Fuel fuel);

    List<FuelGetDto> entityToGetDtoList(List<Fuel> fuelList);
}
