package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.IncomeFuelPostDto;
import com.sigma.oilstation.payload.IncomeFuelDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(IncomeFuelController.INCOME_FUEL_CONTROLLER)
public interface IncomeFuelController {

    String INCOME_FUEL_CONTROLLER = AppConstant.BASE_PATH + "/incomeFuel";

    @PostMapping
    HttpEntity<?> create(@RequestBody IncomeFuelPostDto incomeFuelDto);

    @PutMapping
    HttpEntity<?> edit(@RequestBody IncomeFuelDto incomeFuelDto);

    @PatchMapping("/{id}")
    HttpEntity<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    HttpEntity<?> getById(@PathVariable UUID id);

    @GetMapping
    HttpEntity<?> get();

    @GetMapping("/page")
    HttpEntity<?> getPage(@RequestParam int page, int size);


}
