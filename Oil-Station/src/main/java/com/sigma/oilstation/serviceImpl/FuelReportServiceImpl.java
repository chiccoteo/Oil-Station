package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Device;
import com.sigma.oilstation.entity.Fuel;
import com.sigma.oilstation.entity.FuelReport;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.FuelReportMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.FuelReportDto;
import com.sigma.oilstation.payload.FuelReportPostDto;
import com.sigma.oilstation.repository.DeviceRepository;
import com.sigma.oilstation.repository.FuelReportRepository;
import com.sigma.oilstation.repository.FuelRepository;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.service.FuelReportService;
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
public class FuelReportServiceImpl implements FuelReportService {
    private final FuelReportRepository fuelReportRepository;
    private final FuelRepository fuelRepository;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final FuelReportMapper mapper;


    @Override
    public ApiResponse<?> create(FuelReportPostDto fuelPostDto) {
        Optional<User> optionalUser = userRepository.findById(fuelPostDto.getEmployeeId());
        Optional<Fuel> optionalFuel = fuelRepository.findById(fuelPostDto.getFuelId());
        Optional<Device> optionalDevice = deviceRepository.findById(fuelPostDto.getDeviceId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse<>(false, "Ishchi mavjud emas!");
        }
        if (optionalFuel.isEmpty()) {
            return new ApiResponse<>(false, "Yoqilg'i mavjud emas!");
        }
        if (optionalDevice.isEmpty()) {
            return new ApiResponse<>(false, "Device mavjud emas!");
        }

        FuelReport fuelReport = mapper.toEntity(fuelPostDto);
        fuelReport.setFuel(optionalFuel.get());
        fuelReport.setEmployee(optionalUser.get());
        fuelReport.setDevice(optionalDevice.get());
        fuelReportRepository.save(fuelReport);

        FuelReport oldFuelReport = fuelReportRepository.findByActiveShiftTrueAndDeviceId(optionalDevice.get().getId());
        oldFuelReport.setActiveShift(false);
        oldFuelReport.setAmountAtEndOfShift(fuelReport.getAmountAtEndOfShift());
        return new ApiResponse<>(true, "Hisobot saqlandi!");
    }

    @Override
    public ApiResponse<?> edit(FuelReportDto fuelReportDto) {
        Optional<FuelReport> optionalFuelReport = fuelReportRepository.findById(fuelReportDto.getId());
        Optional<User> optionalEmployee = userRepository.findById(fuelReportDto.getEmployeeId());
        Optional<Fuel> optionalFuel = fuelRepository.findById(fuelReportDto.getFuelId());
        Optional<Device> optionalDevice = deviceRepository.findById(fuelReportDto.getDeviceId());

        if (optionalFuelReport.isEmpty())
            return new ApiResponse<>(false, "Hisobot mavjud emas!");
        if (optionalEmployee.isEmpty())
            return new ApiResponse<>(false, "Ishchi mavjud emas!");
        if (optionalFuel.isEmpty())
            return new ApiResponse<>(false, "Yoqilg'i mavjud emas!");
        if (optionalDevice.isEmpty())
            return new ApiResponse<>(false, "Device mavjud emas!");

        FuelReport fuelReport = mapper.toEntity(fuelReportDto);
        fuelReport.setFuel(optionalFuel.get());
        fuelReport.setEmployee(optionalEmployee.get());
        fuelReport.setDevice(optionalDevice.get());
        fuelReportRepository.save(fuelReport);
        return new ApiResponse<>(true, "Hisobot saqlandi!");
    }

    @Override
    public ApiResponse<?> getById(UUID id) {
        Optional<FuelReport> optionalFuelReport = fuelReportRepository.findById(id);
        if (optionalFuelReport.isEmpty())
            return new ApiResponse<>(false, "Hisobot mavjud emas!");
        return new ApiResponse<>(true, "Hisobot!", mapper.toDto(optionalFuelReport.get()));
    }


    @Override
    public ApiResponse<?> get() {
        List<FuelReportDto> fuelReportDtoList = fuelReportRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        return new ApiResponse<>(true, "Hamma hisobotlar!", fuelReportDtoList);
    }


    @Override
    public ApiResponse<?> get(int page, int size) {
        try {
            Page<FuelReport> fuelReportPage = fuelReportRepository.findAll(CommandUtils.simplePageable(page, size));
            HashMap<String, Object> response = new HashMap<>();
            response.put("fuelReports", fuelReportPage.getContent().stream().map(mapper::toDto).collect(Collectors.toList()));
            response.put("currentPage", fuelReportPage.getNumber());
            response.put("totalItems", fuelReportPage.getTotalElements());
            response.put("totalPages", fuelReportPage.getTotalElements());
            return new ApiResponse<>(true, "Hisobotlar page!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }
}
