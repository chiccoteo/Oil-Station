package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(MeasurementController.MEASUREMENT_CONTROLLER)
public interface MeasurementController {

    String MEASUREMENT_CONTROLLER = AppConstant.BASE_PATH + "/measurement";

    String GET_ALL_MEASUREMENTS = "/all";

    String GET_MEASUREMENT_BY_ID = "/get";

    String UPDATE_MEASUREMENT_BY_ID = "/update";

    String DELETE_MEASUREMENT_BY_ID = "/delete";

    @PostMapping
    HttpEntity<?> addMeasurement(@RequestParam(name = "name") String name);

    @GetMapping(GET_ALL_MEASUREMENTS)
    HttpEntity<?> getAllMeasurements(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                  @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_MEASUREMENT_BY_ID)
    HttpEntity<?> getMeasurementById(@RequestParam(name = "measurementId") Long id);

    @PutMapping(UPDATE_MEASUREMENT_BY_ID)
    HttpEntity<?> editMeasurementById(@RequestParam(name = "measurementId") Long id,
                                   @RequestParam(name = "name") String name);

    @DeleteMapping(DELETE_MEASUREMENT_BY_ID)
    HttpEntity<?> deleteMeasurementById(@RequestParam(name = "measurementId") Long id);
}
