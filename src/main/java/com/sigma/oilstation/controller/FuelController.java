package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.FuelPostDto;
import com.sigma.oilstation.payload.FuelUpdateDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.*;

@RequestMapping(FuelController.FUEL_CONTROLLER)
public interface FuelController {

    String FUEL_CONTROLLER = AppConstant.BASE_PATH + "/fuel";

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @PostMapping(POST_PATH)
    HttpEntity<?> addFuel(@RequestBody FuelPostDto fuelPostDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_BY_ID_PATH)
    HttpEntity<?> getByIdFuel(@PathVariable UUID id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ALL_PAGEABLE_PATH)
    HttpEntity<?> getAllFuelPageable(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @GetMapping(GET_ALL_PATH)
    HttpEntity<?> getAllFuel();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @PutMapping(PUT_PATH)
    HttpEntity<?> updateFuel(@PathVariable UUID id, @RequestBody FuelUpdateDto fuelUpdateDto);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
    @DeleteMapping(DELETE_PATH)
    HttpEntity<?> deleteFuel(@PathVariable UUID id);
}
