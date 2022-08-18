package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.FuelController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.*;

@RestController
@RequiredArgsConstructor
public class FuelControllerImpl implements FuelController {
    @PostMapping(POST_PATH)
    public HttpEntity<?> addProduct(){
        return null;
    }

    @GetMapping(GET_BY_ID_PATH)
    public HttpEntity<?> getByIdProduct(@PathVariable UUID id){
        return null;
    }

    @GetMapping(GET_ALL_PATH)
    public HttpEntity<?> getAllProduct(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                       @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size){

        return null;
    }

    @PutMapping(PUT_PATH)
    public HttpEntity<?> updateProduct(@PathVariable UUID id){

        return null;
    }

    @DeleteMapping(DELETE_PATH)
    public HttpEntity<?> deleteProduct(@PathVariable UUID id){

        return null;
    }
}
