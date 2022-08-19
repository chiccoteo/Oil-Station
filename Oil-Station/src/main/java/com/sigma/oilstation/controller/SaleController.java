package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(SaleController.SALE_CONTROLLER)
public interface SaleController {

    String SALE_CONTROLLER = AppConstant.BASE_PATH + "/sale";

    String GET_ALL_SALES = "/all";

    String GET_SALE_BY_ID = "/get";

    String UPDATE_SALE_BY_ID = "/update";

    String DELETE_SALE_BY_ID = "/delete";

    @PostMapping
    HttpEntity<?> addSale(@RequestParam(name = "name") String name);

    @GetMapping(GET_ALL_SALES)
    HttpEntity<?> getAllSales(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                  @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_SALE_BY_ID)
    HttpEntity<?> getSaleById(@RequestParam(name = "saleId") Long id);

    @PutMapping(UPDATE_SALE_BY_ID)
    HttpEntity<?> editSaleById(@RequestParam(name = "saleId") Long id,
                                   @RequestParam(name = "name") String name);

    @DeleteMapping(DELETE_SALE_BY_ID)
    HttpEntity<?> deleteSaleById(@RequestParam(name = "saleId") Long id);
}
