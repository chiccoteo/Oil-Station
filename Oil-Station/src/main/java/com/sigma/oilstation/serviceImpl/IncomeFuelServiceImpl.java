package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Debt;
import com.sigma.oilstation.entity.Fuel;
import com.sigma.oilstation.entity.IncomeFuel;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.IncomeFuelMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.IncomeFuelPostDto;
import com.sigma.oilstation.payload.IncomeFuelDto;
import com.sigma.oilstation.repository.FuelRepository;
import com.sigma.oilstation.repository.IncomeFuelRepository;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.service.IncomeFuelService;
import com.sigma.oilstation.utils.CommandUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeFuelServiceImpl implements IncomeFuelService {

    private final IncomeFuelRepository incomeFuelRepository;
    private final UserRepository userRepository;
    private final IncomeFuelMapper incomeMapper;
    private final FuelRepository fuelRepository;


    @Override
    public ApiResponse<?> create(IncomeFuelPostDto incomeFuelDto) {
        Optional<User> optionalUser = userRepository.findById(incomeFuelDto.getEmployeeId());
        Optional<Fuel> optionalFuel = fuelRepository.findById(incomeFuelDto.getFuelId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse<>(false, "ishchi mavjud emas!");
        }
        if (optionalFuel.isEmpty()) {
            return new ApiResponse<>(false, "Yoqilg'i mavjud emas!");
        }
        IncomeFuel incomeFuel = incomeMapper.toEntity(incomeFuelDto);
        incomeFuel.setEmployee(optionalUser.get());
        incomeFuelRepository.save(incomeFuel);
        Fuel fuel = optionalFuel.get();
        fuel.setPrice(incomeFuel.getSalePrice());
        fuelRepository.save(fuel);
        if (incomeFuel.isDebt()) {
            Debt debt = new Debt();
            //TODO Umidjon shu yerga qarzni yozish kere
            //TODO men qarzga uncha tushunmadim, help qivoring))
        }
        return new ApiResponse<>(true, "Kirim saqlandi");
    }

    @Override
    public ApiResponse<?> edit(IncomeFuelDto incomeFuelDto) {
        Optional<User> optionalUser = userRepository.findById(incomeFuelDto.getEmployeeId());
        Optional<IncomeFuel> optionalIncomeFuel = incomeFuelRepository.findById(incomeFuelDto.getId());
        Optional<Fuel> optionalFuel = fuelRepository.findById(incomeFuelDto.getFuelId());
        if (optionalUser.isEmpty())
            return new ApiResponse<>(false, "ishchi mavjud emas!");
        if (optionalFuel.isEmpty())
            return new ApiResponse<>(false, "Yoqilg'i mavjud emas!");
        if (optionalIncomeFuel.isEmpty())
            return new ApiResponse<>(false, "Kirim mavjud emas!");

        IncomeFuel incomeFuel = incomeMapper.toEntity(incomeFuelDto);
        Fuel fuel = optionalFuel.get();
        fuel.setPrice(incomeFuel.getSalePrice());
        fuelRepository.save(fuel);
        incomeFuel.setFuel(optionalFuel.get());
        incomeFuel.setEmployee(optionalUser.get());
        incomeFuelRepository.save(incomeFuel);
        return new ApiResponse<>(true, "Kirim tahrirlandi!");
    }

    @Override
    public ApiResponse<?> getById(UUID id) {
        Optional<IncomeFuel> optionalIncomeFuel = incomeFuelRepository.findById(id);
        if (optionalIncomeFuel.isEmpty())
            return new ApiResponse<>(true, "Kirim mavjud emas!");
        IncomeFuelDto incomeFuelDto = incomeMapper.toDto(optionalIncomeFuel.get());
        return new ApiResponse<>(true, "Kirim!", incomeFuelDto);
    }

    @Override
    public ApiResponse<?> get() {
        List<IncomeFuelDto> incomeFuelDtoList = incomeFuelRepository.findAll()
                .stream().map(incomeMapper::toDto)
                .collect(Collectors.toList());
        return new ApiResponse<>(true, "Hamma kirimlar!",incomeFuelDtoList);
    }

    @Override
    public ApiResponse<?> get(int page, int size) {
        try {
            Page<IncomeFuel> incomeFuels = incomeFuelRepository.findAll(CommandUtils.simplePageable(page, size));
            HashMap<String,Object> response = new HashMap<>();
            response.put("incomeFuels",  incomeFuels.getContent().stream().map(incomeMapper::toDto).collect(Collectors.toList()));
            response.put("currentPage", incomeFuels.getNumber());
            response.put("totalItems", incomeFuels.getTotalElements());
            response.put("totalPages", incomeFuels.getTotalPages());
            return new ApiResponse<>(true,"Kirimlar page!",response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false,e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> delete(UUID id) {
        try {
            incomeFuelRepository.deleteById(id);
            return new ApiResponse<>(true,"Kirim o'chirildi!");
        }
        catch (Exception e){
            return new ApiResponse<>(false,"Kirim mavjud emas!");
        }
    }
}
