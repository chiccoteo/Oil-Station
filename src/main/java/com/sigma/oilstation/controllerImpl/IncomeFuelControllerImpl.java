package com.sigma.oilstation.controllerImpl;

import com.sigma.oilstation.controller.IncomeFuelController;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.DebtPostDto;
import com.sigma.oilstation.payload.IncomeFuelPostDto;
import com.sigma.oilstation.payload.IncomeFuelDto;
import com.sigma.oilstation.service.IncomeFuelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class IncomeFuelControllerImpl implements IncomeFuelController {
    private final IncomeFuelService incomeFuelService;

    @Override
    public HttpEntity<?> create(IncomeFuelPostDto incomeFuelDto) {
        ApiResponse<?> response = incomeFuelService.create(incomeFuelDto);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @Override
    public HttpEntity<?> edit( UUID id, IncomeFuelDto incomeFuelDto) {
        ApiResponse<?> response = incomeFuelService.edit(id,incomeFuelDto);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @Override
    public HttpEntity<?> getById(UUID id) {
        ApiResponse<?> response = incomeFuelService.getById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> get() {
        ApiResponse<?> response = incomeFuelService.get();
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> getPage(int page, int size) {
        ApiResponse<?> response = incomeFuelService.get(page,size);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> delete(UUID id) {
        ApiResponse<?> response = incomeFuelService.delete(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> getDailyIncomeFuel(int page, int size) {
        ApiResponse<?> response = incomeFuelService.getDailyIncomeFuel(page, size);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> getWeeklyIncomeFuel(int page, int size) {
        ApiResponse<?> response = incomeFuelService.getWeeklyIncomeFuel(page, size);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> getMonthlyIncomeFuel(int page, int size) {
        ApiResponse<?> response = incomeFuelService.getMonthlyIncomeFuel(page, size);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> getAnnualIncomeFuel(int page, int size) {
        ApiResponse<?> response = incomeFuelService.getAnnuallyIncomeFuel(page, size);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @Override
    public HttpEntity<?> getInterimIncomeFuel(int page, int size, Date startDate, Date endDate) {
        ApiResponse<?> response = incomeFuelService.getInterimIncomeFuel(page, size,startDate,endDate);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
