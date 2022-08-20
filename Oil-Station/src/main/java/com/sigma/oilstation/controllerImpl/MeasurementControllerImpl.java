package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.MeasurementController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeasurementControllerImpl implements MeasurementController {

    @Override
    public HttpEntity<?> addMeasurement(String name) {
        return null;
    }

    @Override
    public HttpEntity<?> getAllMeasurements(Integer page, Integer size) {
        return null;
    }

    @Override
    public HttpEntity<?> getMeasurementById(Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> editMeasurementById(Long id, String name) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteMeasurementById(Long id) {
        return null;
    }

}
