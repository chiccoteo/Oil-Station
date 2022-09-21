package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Measurement;
import com.sigma.oilstation.mapper.MeasurementMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.MeasurementDto;
import com.sigma.oilstation.payload.MeasurementGetDto;
import com.sigma.oilstation.repository.MeasurementRepository;
import com.sigma.oilstation.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final MeasurementMapper measurementMapper;
    @Override
    public ApiResponse<?> addSupplier(String name) {
        Measurement measurement = new Measurement();
        measurement.setName(name);
        measurement.setDeleted(false);
        measurementRepository.save(measurement);
        return ApiResponse.successResponse("SUCCESSFULLY_ADDED", measurementMapper.toGetDto(measurementRepository.save(measurement)));
    }

    @Override
    public ApiResponse<?> getAllMeasurements(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Measurement> all = measurementRepository.findAll(pageable);

        List<MeasurementGetDto> measurementDtos = measurementMapper.toDoList(all.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("measurements", measurementDtos.stream().filter(measurementGetDto -> !measurementGetDto.isDeleted()).collect(Collectors.toList()));
        response.put("currentPage", all.getNumber());
        response.put("totalItems", all.getTotalElements());
        response.put("totalPages", all.getTotalPages());

        return ApiResponse.successResponse("ALL_MEASUREMENTS_PAGES", response);
    }

    @Override
    public ApiResponse<?> getMeasurementById(Long id) {
        if (id==null)
            return ApiResponse.errorResponse("IT_MUST_NOT_NULL");

        Optional<Measurement> optional = measurementRepository.findById(id);

        if (optional.isEmpty())
            return ApiResponse.errorResponse("THIS_MEASUREMENT_DOES_NOT_EXIST");

        return ApiResponse.successResponse("SUCCESS", measurementMapper.toGetDto(optional.get()));
    }

    @Override
    public ApiResponse<?> editMeasurementById(Long id, String name) {
        if (id==null)
            return ApiResponse.errorResponse("IT_MUST_NOT_NULL");

        Optional<Measurement> optional = measurementRepository.findById(id);

        if (optional.isEmpty())
            return ApiResponse.errorResponse("THIS_MEASUREMENT_DOES_NOT_EXIST");

        Measurement measurement = optional.get();
        measurement.setName(name);
        measurementRepository.save(measurement);
        return ApiResponse.successResponse("SUCCESSFULLY_EDITED");

    }

    @Override
    public ApiResponse<?> deleteMeasurementById(Long id) {
        if (id==null)
            return ApiResponse.errorResponse("IT_MUST_NOT_NULL");

        Optional<Measurement> optional = measurementRepository.findById(id);

        if (optional.isEmpty())
            return ApiResponse.errorResponse("THIS_MEASUREMENT_DOES_NOT_EXIST");

        Measurement measurement = optional.get();
        measurement.setDeleted(true);
        measurementRepository.save(measurement);

        return ApiResponse.successResponse("SUCCESSFULLY_DELETED");
    }

    @Override
    public ApiResponse<?> getAll() {
        List<Measurement> all = measurementRepository.findAll();
        return ApiResponse.successResponse("ALL_MEASUREMENT",measurementMapper.toDoList(all));
    }
}
