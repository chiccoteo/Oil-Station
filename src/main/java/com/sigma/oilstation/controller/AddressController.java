package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.AddressDTO;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(AddressController.ADDRESS_CONTROLLER)
public interface AddressController {

    String ADDRESS_CONTROLLER = AppConstant.BASE_PATH + "/address";

    @GetMapping("/all")
    HttpEntity<?> getAll(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                         @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping("/getAllList")
    HttpEntity<?> getAllList();

    @GetMapping("/get")
    HttpEntity<?> getById(@RequestParam(name = "byId") Long id);

    @PostMapping()
    HttpEntity<?> create(@RequestBody AddressDTO addressDTO);

    @PutMapping("/update/{id}")
    HttpEntity<?> update(@RequestBody AddressDTO addressDTO, @PathVariable Long id);


    @DeleteMapping("/delete/{id}")
    HttpEntity<?> delete(@PathVariable Long id);
}
