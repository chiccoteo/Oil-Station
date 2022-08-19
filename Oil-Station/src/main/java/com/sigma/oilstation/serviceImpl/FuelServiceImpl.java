package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Fuel;
import com.sigma.oilstation.entity.Measurement;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.FuelMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelGetDto;
import com.sigma.oilstation.payload.FuelPostDto;
import com.sigma.oilstation.payload.FuelUpdateDto;
import com.sigma.oilstation.repository.FuelRepository;
import com.sigma.oilstation.repository.MeasurementRepository;
import com.sigma.oilstation.service.FuelService;
import com.sigma.oilstation.utils.CommandUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;

    private final FuelMapper fuelMapper;

    private final MeasurementRepository measurementRepository;
    @Override
    public ApiResponse<?> addFuel(FuelPostDto fuelPostDto) {
//logic
        Fuel fuel = fuelMapper.postDtoToEntity(fuelPostDto);
        fuelRepository.save(fuel);
        return ApiResponse.errorResponse("Successfully saved");
    }

    @Override
    public ApiResponse<?> getByIdFuel(UUID id) {
        Optional<Fuel> optionalFuel = fuelRepository.findById(id);
        if (optionalFuel.isEmpty()){
            return ApiResponse.errorResponse("Such a fuel does not exist");
        }
        Fuel fuel = optionalFuel.get();
        FuelGetDto fuelGetDto = fuelMapper.entityToGetDto(fuel);
        return ApiResponse.successResponse("Success", fuelGetDto);
    }

    @Override
    public ApiResponse<?> getAllFuel(Integer page, Integer size) {
        Page<Fuel> fuelPage;
        try {
            fuelPage = fuelRepository.findAll(CommandUtils.simplePageable(page, size));
        }catch (PageSizeException e){
            return ApiResponse.errorResponse(e.getMessage());
        }
        Map<String, Object> response = new HashMap<>();
        List<FuelGetDto> fuelGetDtoList = fuelMapper.entityToGetDtoList(fuelPage.getContent());
        response.put("fuel", fuelGetDtoList);
        response.put("currentPage", fuelPage.getNumber());
        response.put("totalItems", fuelPage.getTotalElements());
        response.put("totalPages", fuelPage.getTotalPages());
        return ApiResponse.successResponse("All fuel with page", response);
    }

    @Override
    public ApiResponse<?> updateFuel(UUID id, FuelUpdateDto fuelUpdateDto) {
        Optional<Fuel> optionalFuel = fuelRepository.findById(id);
        if (optionalFuel.isEmpty()){
            return ApiResponse.errorResponse("Such a fuel does not exist");
        }
        Optional<Measurement> optionalIncomeMeasurement = measurementRepository.findById(fuelUpdateDto.getIncomeMeasurementId());
        if (optionalIncomeMeasurement.isEmpty()){
            return ApiResponse.errorResponse("Such a income measurement does not exist");
        }
        Measurement incomeMeasurement = optionalIncomeMeasurement.get();
        Optional<Measurement> optionalOutComeMeasurement = measurementRepository.findById(fuelUpdateDto.getIncomeMeasurementId());
        if (optionalOutComeMeasurement.isEmpty()){
            return ApiResponse.errorResponse("Such a outcome measurement does not exist");
        }
        Measurement outcomeMeasurement = optionalOutComeMeasurement.get();
        Fuel fuel = optionalFuel.get();
        fuel.setName(fuelUpdateDto.getName());
        fuel.setType(fuelUpdateDto.getType());
        fuel.setPrice(fuelUpdateDto.getPrice());
        fuel.setIncomeMeasurement(incomeMeasurement);
        fuel.setOutcomeMeasurement(outcomeMeasurement);
        fuelRepository.save(fuel);
        return ApiResponse.successResponse("Successfully updated");
    }

    @Override
    public ApiResponse<?> deleteFuel(UUID id) {
        return null;
    }
}
