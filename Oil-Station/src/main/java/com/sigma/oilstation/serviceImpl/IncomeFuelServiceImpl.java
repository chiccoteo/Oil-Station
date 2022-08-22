package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.*;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.IncomeFuelMapper;
import com.sigma.oilstation.payload.*;
import com.sigma.oilstation.repository.FuelRepository;
import com.sigma.oilstation.repository.IncomeFuelRepository;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.service.IncomeFuelService;
import com.sigma.oilstation.utils.CommandUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
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

        Fuel fuel = optionalFuel.get();
        fuel.setPrice(incomeFuel.getSalePrice());
        fuelRepository.saveAndFlush(fuel);

        incomeFuel.setEmployee(optionalUser.get());
        incomeFuel.setFuel(fuel);
        incomeFuelRepository.save(incomeFuel);

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
        return new ApiResponse<>(true, "Hamma kirimlar!", getTotalIncomeFuel(incomeFuelRepository.findAll()));
    }

    @Override
    public ApiResponse<?> get(int page, int size) {
        try {
            Page<IncomeFuel> incomeFuels = incomeFuelRepository.findAll(CommandUtils.simplePageable(page, size));
            HashMap<String, Object> response = new HashMap<>();
            response.put("incomeFuels", getTotalIncomeFuel(incomeFuels));
            response.put("currentPage", incomeFuels.getNumber());
            response.put("totalItems", incomeFuels.getTotalElements());
            response.put("totalPages", incomeFuels.getTotalPages());
            return new ApiResponse<>(true, "Kirimlar page!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> delete(UUID id) {
        try {
            incomeFuelRepository.deleteById(id);
            return new ApiResponse<>(true, "Kirim o'chirildi!");
        } catch (Exception e) {
            return new ApiResponse<>(false, "Kirim mavjud emas!");
        }
    }

    @Override
    public ApiResponse<?> getDailyIncomeFuel(int page, int size) {
        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp yesterday = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        try {
            Page<IncomeFuel> fuelReportPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(today, yesterday, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(fuelReportPage));
            response.put("currentPage", fuelReportPage.getNumber());
            response.put("totalItems", fuelReportPage.getTotalElements());
            response.put("totalPages", fuelReportPage.getTotalPages());
            return new ApiResponse<>(true, "Kunlik kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getWeeklyIncomeFuel(int page, int size) {
        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp aWeekAgo = Timestamp.valueOf(LocalDateTime.now().minusWeeks(1));
        try {
            Page<IncomeFuel> fuelReportPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(today, aWeekAgo, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(fuelReportPage));
            response.put("currentPage", fuelReportPage.getNumber());
            response.put("totalItems", fuelReportPage.getTotalElements());
            response.put("totalPages", fuelReportPage.getTotalPages());
            return new ApiResponse<>(true, "Haftalik kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getMonthlyIncomeFuel(int page, int size) {
        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp aMonthAgo = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
        try {
            Page<IncomeFuel> fuelReportPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(today, aMonthAgo, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(fuelReportPage));
            response.put("currentPage", fuelReportPage.getNumber());
            response.put("totalItems", fuelReportPage.getTotalElements());
            response.put("totalPages", fuelReportPage.getTotalPages());
            return new ApiResponse<>(true, "Oylik kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getAnnuallyIncomeFuel(int page, int size) {
        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp aYearAgo = Timestamp.valueOf(LocalDateTime.now().minusYears(1));
        try {
            Page<IncomeFuel> fuelReportPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(today, aYearAgo, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(fuelReportPage));
            response.put("currentPage", fuelReportPage.getNumber());
            response.put("totalItems", fuelReportPage.getTotalElements());
            response.put("totalPages", fuelReportPage.getTotalPages());
            return new ApiResponse<>(true, "Yillik kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }


    @Override
    public ApiResponse<?> getInterimIncomeFuel(int page, int size, Date startDate, Date endDate) {
        try {
            Page<IncomeFuel> fuelReportPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(new Timestamp(startDate.getTime()),new Timestamp(endDate.getTime()), CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(fuelReportPage));
            response.put("currentPage", fuelReportPage.getNumber());
            response.put("totalItems", fuelReportPage.getTotalElements());
            response.put("totalPages", fuelReportPage.getTotalPages());
            return new ApiResponse<>(true, "Vaqt oraligidagi kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }





    IncomeFuelTotalDto getTotalIncomeFuel(Page<IncomeFuel> fuelReportPage){
        List<IncomeFuelDto> incomeFuelDtoList = fuelReportPage.getContent().stream().map(incomeMapper::toDto).collect(Collectors.toList());
        double totalSumSale=0;
        double totalSumIncome=0;
        double totalAmount=0;
        for (IncomeFuelDto incomeFuelDto : incomeFuelDtoList) {
            totalSumSale+=(incomeFuelDto.getAmount()*incomeFuelDto.getSalePrice());
            totalSumIncome+=(incomeFuelDto.getAmount()*incomeFuelDto.getIncomePrice());
            totalAmount+=incomeFuelDto.getAmount();
        }
        return new IncomeFuelTotalDto(totalSumIncome,totalSumSale,totalAmount,incomeFuelDtoList);
    }
    IncomeFuelTotalDto getTotalIncomeFuel(List<IncomeFuel> fuelReportList){
        List<IncomeFuelDto> incomeFuelDtoList = fuelReportList.stream().map(incomeMapper::toDto).collect(Collectors.toList());
        double totalSumIncome=0;
        double totalSumSale=0;
        double totalAmount=0;
        for (IncomeFuelDto incomeFuelDto : incomeFuelDtoList) {
            totalSumSale+=(incomeFuelDto.getAmount()*incomeFuelDto.getSalePrice());
            totalSumIncome+=(incomeFuelDto.getAmount()*incomeFuelDto.getIncomePrice());
            totalAmount+=incomeFuelDto.getAmount();
        }
        return new IncomeFuelTotalDto(totalSumIncome,totalSumSale,totalAmount,incomeFuelDtoList);
    }
}
