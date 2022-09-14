package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.OutgoingController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.OutgoingPostDTO;
import com.sigma.oilstation.service.OutgoingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OutgoingControllerImpl implements OutgoingController {

    private final OutgoingService outgoingService;

    @Override
    public HttpEntity<?> createOutgoing(OutgoingPostDTO outgoingPostDTO) {
        ApiResponse<?> apiResponse = outgoingService.createOutgoing(outgoingPostDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAllOutgoings(Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getAllOutgoings(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getListOfOutgoings() {
        ApiResponse<?> apiResponse = outgoingService.getListOfOutgoings();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public HttpEntity<?> getOutgoingById(UUID id) {
        ApiResponse<?> apiResponse = outgoingService.getOutgoingById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getOutgoingByBranchId(UUID id, Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getOutgoingByBranchId(id, page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getDailyOutgoingsByBranchId(UUID id, Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getDailyOutgoingsByBranchId(id, page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getWeeklyOutgoingsByBranchId(UUID id, Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getWeeklyOutgoingsByBranchId(id, page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getMonthlyOutgoingsByBranchId(UUID id, Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getMonthlyOutgoingsByBranchId(id, page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAnnualOutgoingsByBranchId(UUID id, Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getAnnualOutgoingsByBranchId(id, page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getInterimOutgoingsByBranchId(UUID id, Integer page, Integer size, Date startDate, Date endDate) {
        Timestamp startTimestamp = new Timestamp(startDate.getTime());
        Timestamp endTimestamp = new Timestamp(endDate.getTime());
        ApiResponse<?> apiResponse = outgoingService.getInterimOutgoingsByBranchId(id, page, size, startTimestamp, endTimestamp);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> updateOutgoingById(UUID id, OutgoingPostDTO outgoingPostDTO) {
        ApiResponse<?> apiResponse = outgoingService.updateOutgoingById(id, outgoingPostDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> deleteOutgoingById(UUID id) {
        ApiResponse<?> apiResponse = outgoingService.deleteOutgoingById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getDailyOutgoings(Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getDailyOutgoings(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getWeeklyOutgoings(Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getWeeklyOutgoings(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getMonthlyOutgoings(Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getMonthlyOutgoings(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getAnnualOutgoings(Integer page, Integer size) {
        ApiResponse<?> apiResponse = outgoingService.getAnnualOutgoings(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> getInterimOutgoings(Integer page, Integer size, Date startDate, Date endDate) {
        Timestamp startTimestamp = new Timestamp(startDate.getTime());
        Timestamp endTimestamp = new Timestamp(endDate.getTime());
        ApiResponse<?> apiResponse = outgoingService.getInterimOutgoings(startTimestamp, endTimestamp, page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
