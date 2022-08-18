package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Outgoing;
import com.sigma.oilstation.entity.OutgoingCategory;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.OutgoingMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.OutgoingPostDTO;
import com.sigma.oilstation.repository.OutgoingCategoryRepository;
import com.sigma.oilstation.repository.OutgoingRepository;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.service.OutgoingService;
import com.sigma.oilstation.utils.CommandUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutgoingServiceImpl implements OutgoingService {

    private final OutgoingRepository outgoingRepo;

    private final OutgoingCategoryRepository outgoingCategoryRepo;

    private final OutgoingMapper outgoingMapper;

    private final UserRepository userRepo;

    @Override
    public ApiResponse<?> createOutgoing(OutgoingPostDTO outgoingPostDTO) {
        Optional<User> optionalUser = userRepo.findById(outgoingPostDTO.getSpenderId());
        if (optionalUser.isEmpty())
            return ApiResponse.errorResponse("Such a user does not exist");
        Outgoing outgoing = outgoingMapper.toEntity(outgoingPostDTO);
        outgoing.setSpender(optionalUser.get());
        Optional<OutgoingCategory> optionalOutgoingCategory = outgoingCategoryRepo.findByName(outgoing.getCategory().getName());
        if (optionalOutgoingCategory.isPresent())
            outgoing.setCategory(optionalOutgoingCategory.get());
        else
            outgoing.setCategory(outgoingCategoryRepo.save(outgoing.getCategory()));
        outgoingRepo.save(outgoing);
        return ApiResponse.successResponse("Successfully saved");
    }

    @Override
    public ApiResponse<?> getAllOutgoings(Integer page, Integer size) {
        Page<Outgoing> outgoingPage;
        try {
            outgoingPage = outgoingRepo.findAll(CommandUtils.simplePageable(page, size));
        } catch (PageSizeException e) {
            return ApiResponse.errorResponse(e.getMessage());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("outgoings", outgoingMapper.toGetDTOList(outgoingPage.getContent()));
        response.put("currentPage", outgoingPage.getNumber());
        response.put("totalItems", outgoingPage.getTotalElements());
        response.put("totalPages", outgoingPage.getTotalPages());
        return ApiResponse.successResponse("All outgoings with page", response);
    }

    @Override
    public ApiResponse<?> getOutgoingById(UUID id) {
        Optional<Outgoing> optionalOutgoing = outgoingRepo.findById(id);
        if (optionalOutgoing.isEmpty())
            return ApiResponse.errorResponse("Such an outgoing does not exist");
        return ApiResponse.successResponse("Success", outgoingMapper.toGetDTO(optionalOutgoing.get()));
    }

    @Override
    public ApiResponse<?> updateOutgoingById(UUID id, OutgoingPostDTO outgoingPostDTO) {
        Optional<Outgoing> optionalOutgoing = outgoingRepo.findById(id);
        if (optionalOutgoing.isEmpty())
            return ApiResponse.errorResponse("Such an outgoing does not exist");
        Outgoing outgoing = optionalOutgoing.get();
        outgoing.setName(outgoingPostDTO.getName());
        outgoing.setDescription(outgoingPostDTO.getDescription());
        outgoing.setAmount(outgoingPostDTO.getAmount());
        Optional<User> optionalUser = userRepo.findById(outgoingPostDTO.getSpenderId());
        if (optionalUser.isEmpty())
            return ApiResponse.errorResponse("Such a user does not exist");
        outgoing.setSpender(optionalUser.get());
        outgoing.setOutgoingTime(outgoingPostDTO.getOutgoingTime());
        Optional<OutgoingCategory> optionalOutgoingCategory = outgoingCategoryRepo.findByName(outgoingPostDTO.getOutgoingCategoryName());
        if (optionalOutgoingCategory.isPresent())
            outgoing.setCategory(optionalOutgoingCategory.get());
        else
            outgoing.setCategory(outgoingCategoryRepo.save(new OutgoingCategory(outgoingPostDTO.getOutgoingCategoryName())));
        outgoingRepo.save(outgoing);
        return ApiResponse.successResponse("Successfully updated");
    }

    @Override
    public ApiResponse<?> deleteOutgoingById(UUID id) {
        if (!outgoingRepo.existsById(id))
            return ApiResponse.errorResponse("Such an outgoing does not exist");
        outgoingRepo.deleteById(id);
        return ApiResponse.successResponse("Successfully deleted");
    }
}
