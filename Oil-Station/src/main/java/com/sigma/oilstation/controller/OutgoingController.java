package com.sigma.oilstation.controller;

import com.sigma.oilstation.payload.OutgoingPostDTO;
import com.sigma.oilstation.utils.AppConstant;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

import static com.sigma.oilstation.utils.AppConstant.DEFAULT_PAGE;
import static com.sigma.oilstation.utils.AppConstant.DEFAULT_SIZE;

@RequestMapping(OutgoingController.OUTGOING_CONTROLLER)
public interface OutgoingController {

    String OUTGOING_CONTROLLER = AppConstant.BASE_PATH + "/outgoing";

    String GET_ALL_OUTGOINGS = "/all";

    String GET_LIST_OF_OUTGOINGS = "/list";

    String GET_OUTGOING_BY_ID = "/get";

    String UPDATE_OUTGOING_BY_ID = "/update";

    String DELETE_OUTGOING_BY_ID = "/delete";

    String GET_DAILY_OUTGOINGS = "/daily";

    String GET_WEEKLY_OUTGOINGS = "/weekly";

    String GET_MONTHLY_OUTGOINGS = "/monthly";

    String GET_ANNUAL_OUTGOINGS = "/annual";

    String GET_BETWEEN_OUTGOINGS = "/between";


    @PostMapping
    HttpEntity<?> createOutgoing(@RequestBody OutgoingPostDTO outgoingPostDTO);

    @GetMapping(GET_ALL_OUTGOINGS)
    HttpEntity<?> getAllOutgoings(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                  @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_LIST_OF_OUTGOINGS)
    HttpEntity<?> getListOfOutgoings();

    @GetMapping(GET_OUTGOING_BY_ID)
    HttpEntity<?> getOutgoingById(@RequestParam(name = "outgoingId") UUID id);

    @PutMapping(UPDATE_OUTGOING_BY_ID)
    HttpEntity<?> updateOutgoingById(@RequestParam(name = "outgoingId") UUID id,
                                     @RequestBody OutgoingPostDTO outgoingPostDTO);

    @DeleteMapping(DELETE_OUTGOING_BY_ID)
    HttpEntity<?> deleteOutgoingById(@RequestParam(name = "outgoingId") UUID id);

    @GetMapping(GET_DAILY_OUTGOINGS)
    HttpEntity<?> getDailyOutgoings(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                    @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_WEEKLY_OUTGOINGS)
    HttpEntity<?> getWeeklyOutgoings(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_MONTHLY_OUTGOINGS)
    HttpEntity<?> getMonthlyOutgoings(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                      @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_ANNUAL_OUTGOINGS)
    HttpEntity<?> getAnnualOutgoings(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size);

    @GetMapping(GET_BETWEEN_OUTGOINGS)
    HttpEntity<?> getInterimOutgoings(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);

}
