package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.FuelPostDto;
import com.sigma.oilstation.payload.FuelUpdateDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.*;

@RequestMapping(FuelController.FUEL_CONTROLLER)
public interface FuelController {

    String FUEL_CONTROLLER = AppConstant.BASE_PATH + "/fuel";

    @PostMapping(POST_PATH)
    public HttpEntity<?> addFuel(@RequestBody FuelPostDto fuelPostDto);

    @GetMapping(GET_BY_ID_PATH)
    public HttpEntity<?> getByIdFuel(@PathVariable UUID id);

    @GetMapping(GET_ALL_PATH)
    public HttpEntity<?> getAllFuel(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                       @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PutMapping(PUT_PATH)
    public HttpEntity<?> updateFuel(@PathVariable UUID id, @RequestBody FuelUpdateDto fuelUpdateDto);

    @DeleteMapping(DELETE_PATH)
    public HttpEntity<?> deleteFuel(@PathVariable UUID id);
}
