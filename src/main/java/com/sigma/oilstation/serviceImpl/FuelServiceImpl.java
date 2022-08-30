package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Fuel;
import com.sigma.oilstation.entity.Measurement;
import com.sigma.oilstation.exceptions.PageSizeException;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;

    private final MeasurementRepository measurementRepository;
    @Override
    public ApiResponse<?> addFuel(FuelPostDto fuelPostDto) {
        Optional<Fuel> optionalFuel = fuelRepository.findByType(fuelPostDto.getType());
        if (optionalFuel.isPresent() && !optionalFuel.get().isDeleted()){
            return ApiResponse.errorResponse("Such a type already exist");
        }
        Optional<Measurement> optionalIncomeMeasurement = measurementRepository.findById(fuelPostDto.getIncomeMeasurementId());
        if (optionalIncomeMeasurement.isEmpty()){
            return ApiResponse.errorResponse("Such a income measurement does not exist");
        }
        Measurement incomeMeasurement = optionalIncomeMeasurement.get();
        Optional<Measurement> optionalOutComeMeasurement = measurementRepository.findById(fuelPostDto.getOutcomeMeasurementId());
        if (optionalOutComeMeasurement.isEmpty()){
            return ApiResponse.errorResponse("Such a outcome measurement does not exist");
        }
        Measurement outcomeMeasurement = optionalOutComeMeasurement.get();
        Fuel fuel = new Fuel();
        fuel.setName(fuelPostDto.getName());
        fuel.setType(fuelPostDto.getType());
        fuel.setPrice(fuelPostDto.getPrice());
        fuel.setIncomeMeasurement(incomeMeasurement);
        fuel.setOutcomeMeasurement(outcomeMeasurement);
        fuel.setDeleted(false);
        fuelRepository.save(fuel);
        return ApiResponse.successResponse("Successfully saved");
    }

    @Override
    public ApiResponse<?> getByIdFuel(UUID id) {
        Optional<Fuel> optionalFuel = fuelRepository.findById(id);
        if (optionalFuel.isEmpty()){
            return ApiResponse.errorResponse("Such a fuel does not exist");
        }
        Fuel fuel = optionalFuel.get();
        FuelGetDto fuelGetDto = new FuelGetDto();
        fuelGetDto.setId(fuel.getId());
        fuelGetDto.setName(fuel.getName());
        fuelGetDto.setType(fuel.getType());
        fuelGetDto.setPrice(fuel.getPrice());
        fuelGetDto.setIncomeMeasurementId(fuel.getIncomeMeasurement().getId());
        fuelGetDto.setOutcomeMeasurementId(fuel.getOutcomeMeasurement().getId());
        fuelGetDto.setDeleted(fuel.isDeleted());
        return ApiResponse.successResponse("Success", fuelGetDto);
    }

    @Override
    public ApiResponse<?> getAllFuelPageable(Integer page, Integer size) {
        Page<Fuel> fuelPage;
        try {
            fuelPage = fuelRepository.findAll(CommandUtils.simplePageable(page, size));
        }catch (PageSizeException e){
            return ApiResponse.errorResponse(e.getMessage());
        }
        List<FuelGetDto> fuelGetDtoList = new LinkedList<>();
        List<Fuel> fuelList = fuelPage.getContent();

        for (Fuel fuel : fuelList) {
            FuelGetDto fuelGetDto = new FuelGetDto();
            fuelGetDto.setId(fuel.getId());
            fuelGetDto.setName(fuel.getName());
            fuelGetDto.setType(fuel.getType());
            fuelGetDto.setPrice(fuel.getPrice());
            fuelGetDto.setIncomeMeasurementId(fuel.getIncomeMeasurement().getId());
            fuelGetDto.setOutcomeMeasurementId(fuel.getOutcomeMeasurement().getId());
            fuelGetDto.setDeleted(fuel.isDeleted());
            fuelGetDtoList.add(fuelGetDto);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("fuel", fuelGetDtoList);
        response.put("currentPage", fuelPage.getNumber());
        response.put("totalItems", fuelPage.getTotalElements());
        response.put("totalPages", fuelPage.getTotalPages());
        return ApiResponse.successResponse("All fuel with page", response);
    }

    @Override
    public ApiResponse<?> getAllFuel() {
        List<Fuel> fuelList = fuelRepository.findAll(Sort.by("createdDate"));
        List<FuelGetDto> fuelGetDtoList = new LinkedList<>();
        for (Fuel fuel : fuelList) {
            FuelGetDto fuelGetDto = new FuelGetDto();
            fuelGetDto.setId(fuel.getId());
            fuelGetDto.setName(fuel.getName());
            fuelGetDto.setType(fuel.getType());
            fuelGetDto.setPrice(fuel.getPrice());
            fuelGetDto.setIncomeMeasurementId(fuel.getIncomeMeasurement().getId());
            fuelGetDto.setOutcomeMeasurementId(fuel.getOutcomeMeasurement().getId());
            fuelGetDto.setDeleted(fuel.isDeleted());
            fuelGetDtoList.add(fuelGetDto);
        }
        return ApiResponse.successResponse("All fuel with page", fuelGetDtoList);
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
        Optional<Fuel> optionalFuel = fuelRepository.findById(id);
        if (optionalFuel.isEmpty()){
            return ApiResponse.errorResponse("Such a fuel does not exist");
        }
        Fuel fuel = optionalFuel.get();
        if (fuel.isDeleted()){
            return ApiResponse.errorResponse("Such a fuel already deleted");
        }
        fuel.setDeleted(true);
        fuelRepository.save(fuel);
        return ApiResponse.successResponse("Successfully deleted");
    }
}
