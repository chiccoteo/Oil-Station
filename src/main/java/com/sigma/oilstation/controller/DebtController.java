package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.DebtPostDto;
import com.sigma.oilstation.payload.DebtUpdateDto;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.*;

@RequestMapping(DebtController.DEBT_CONTROLLER)
public interface DebtController {

    String DEBT_CONTROLLER = AppConstant.BASE_PATH + "/debt";

    @PostMapping(POST_PATH)
    public HttpEntity<?> addDebt(@RequestBody DebtPostDto debtPostDto);

    @GetMapping(GET_BY_ID_PATH)
    public HttpEntity<?> getByIdDebt(@PathVariable UUID id);

    @GetMapping(GET_ALL_PAGEABLE_PATH)
    public HttpEntity<?> getAllDebtPageable(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_ALL_PATH)
    public HttpEntity<?> getAllDebt();


    @PutMapping(PUT_PATH)
    public HttpEntity<?> updateDebt(@PathVariable UUID id, @RequestBody DebtUpdateDto debtUpdateDto);


    @DeleteMapping(DELETE_PATH)
    public HttpEntity<?> deleteDebt(@PathVariable UUID id);

}