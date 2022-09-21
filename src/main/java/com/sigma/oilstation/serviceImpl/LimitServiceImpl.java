package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Limit;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.repository.LimitRepository;
import com.sigma.oilstation.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;

    @Override
    public ApiResponse<?> update(Long limit) {
        limitRepository.deleteAll();
        limitRepository.save(new Limit("OilLimit", limit));
        return ApiResponse.successResponse("Updated");
    }

    @Override
    public ApiResponse<?> get() {
        Optional<Limit> debtLimit = limitRepository.findByName("OilLimit");
        Long limit = null;
        if (debtLimit.isPresent())
            limit = debtLimit.get().getOilLimit();
        return ApiResponse.successResponse(limit);
    }
}
