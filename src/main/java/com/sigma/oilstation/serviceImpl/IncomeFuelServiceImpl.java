package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Fuel;
import com.sigma.oilstation.entity.FuelReport;
import com.sigma.oilstation.entity.IncomeFuel;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.IncomeFuelMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.IncomeFuelDto;
import com.sigma.oilstation.payload.IncomeFuelPostDto;
import com.sigma.oilstation.payload.IncomeFuelTotalDto;
import com.sigma.oilstation.repository.*;
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
    private final BranchRepository branchRepository;
    private final FuelReportRepository fuelReportRepository;



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

        User user = optionalUser.get();

        IncomeFuel incomeFuel = incomeMapper.toEntity(incomeFuelDto);
        Fuel fuel = optionalFuel.get();
        if(incomeFuel.getSalePrice()==fuel.getPrice()){
            FuelReport fuelReport = fuelReportRepository.findByActiveShiftTrueAndEmployeeBranchIdAndFuel_Id(user.getId(), fuel.getId());
            if(fuelReport!=null){
                fuelReport.setAmountAtEndOfShift(incomeFuel.getCounter());
                fuelReport.setActiveShift(false);
                fuelReportRepository.save(fuelReport);
            }
            FuelReport newFuelReport = new FuelReport(user,
                    incomeFuel.getCounter()+incomeFuel.getAmount(),fuel,incomeFuel.getSalePrice(),
                    incomeFuel.getIncomeTime(),
                    true
                    );
            fuelReportRepository.save(newFuelReport);
        }
        else{
            List<FuelReport> fuelReportList = fuelReportRepository.findAllByActiveShiftIsTrue();
            for (FuelReport fuelReport : fuelReportList) {
                fuelReport.setAmountAtEndOfShift(incomeFuel.getCounter());
                fuelReport.setActiveShift(false);
                fuelReportRepository.save(fuelReport);
                FuelReport newFuelReport = new FuelReport(user,
                        incomeFuel.getCounter()+incomeFuel.getAmount(),
                        fuel, incomeFuel.getSalePrice(),
                        incomeFuel.getIncomeTime(),
                        true);
                fuelReportRepository.save(newFuelReport);
            }
        }

        fuel.setPrice(incomeFuel.getSalePrice());
        fuelRepository.save(fuel);

        incomeFuel.setEmployee(user);
        incomeFuel.setFuel(fuel);
        incomeFuelRepository.save(incomeFuel);

        return new ApiResponse<>(true, "Kirim saqlandi");
    }

    @Override
    public ApiResponse<?> edit(UUID id, IncomeFuelDto incomeFuelDto) {


        Optional<User> optionalUser = userRepository.findById(incomeFuelDto.getEmployeeId());
        Optional<IncomeFuel> optionalIncomeFuel = incomeFuelRepository.findById(id);
        Optional<Fuel> optionalFuel = fuelRepository.findById(incomeFuelDto.getFuelId());
        if (optionalUser.isEmpty())
            return new ApiResponse<>(false, "ishchi mavjud emas!");
        if (optionalFuel.isEmpty())
            return new ApiResponse<>(false, "Yoqilg'i mavjud emas!");
        if (optionalIncomeFuel.isEmpty())
            return new ApiResponse<>(false, "Kirim mavjud emas!");

        IncomeFuel incomeFuel = optionalIncomeFuel.get();
        User user = optionalUser.get();
        Fuel fuel = optionalFuel.get();

        if(incomeFuel.getSalePrice()==fuel.getPrice()){
            FuelReport fuelReport = fuelReportRepository.findByActiveShiftTrueAndEmployeeBranchIdAndFuel_Id(user.getId(), fuel.getId());
            fuelReport.setAmountAtStartOfShift(incomeFuel.getCounter()+incomeFuel.getAmount());
            fuelReportRepository.save(fuelReport);
        }

        fuel.setPrice(incomeFuel.getSalePrice());
        fuelRepository.save(fuel);

        incomeFuel.setFuel(optionalFuel.get());
        incomeFuel.setEmployee(user);
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
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(yesterday, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
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
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(aWeekAgo, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
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
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(aMonthAgo, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
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
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(aYearAgo, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Yillik kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }


    @Override
    public ApiResponse<?> getInterimIncomeFuel(int page, int size, Date startDate, Date endDate) {

        try {
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByIncomeTimeIsBetween(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()), CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Vaqt oraligidagi kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getByBranchId(UUID branchId, int page, int size) {
        if (!branchRepository.existsById(branchId))
            return new ApiResponse<>(false, "Filial mavjud emas!");

        try {
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByEmployeeBranchId(branchId, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Filial kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getDailyBranchIncomeFuel(UUID branchId, int page, int size) {
        if (!branchRepository.existsById(branchId))
            return new ApiResponse<>(false, "Filial mavjud emas!");

        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp yesterday = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        try {
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByEmployeeBranchIdAndIncomeTimeIsBetween(branchId, yesterday, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Kunlik filial kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getWeeklyBranchIncomeFuel(UUID branchId, int page, int size) {
        if (!branchRepository.existsById(branchId))
            return new ApiResponse<>(false, "Filial mavjud emas!");

        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp aWeekAgo = Timestamp.valueOf(LocalDateTime.now().minusWeeks(1));
        try {
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByEmployeeBranchIdAndIncomeTimeIsBetween(branchId, aWeekAgo, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Haftalik filial kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getMonthlyBranchIncomeFuel(UUID branchId, int page, int size) {
        if (!branchRepository.existsById(branchId))
            return new ApiResponse<>(false, "Filial mavjud emas!");

        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp aMonthAgo = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
        try {
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByEmployeeBranchIdAndIncomeTimeIsBetween(branchId, aMonthAgo, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Oylik filial kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getAnnualBranchIncomeFuel(UUID branchId, int page, int size) {
        if (!branchRepository.existsById(branchId))
            return new ApiResponse<>(false, "Filial mavjud emas!");

        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp aYearAgo = Timestamp.valueOf(LocalDateTime.now().minusYears(1));
        try {
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByEmployeeBranchIdAndIncomeTimeIsBetween(branchId, aYearAgo, today, CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Yillik filial kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse<?> getInterimBranchIncomeFuel(UUID branchId, int page, int size, Date startDate, Date endDate) {
        if (!branchRepository.existsById(branchId))
            return new ApiResponse<>(false, "Filial mavjud emas!");

        try {
            Page<IncomeFuel> incomeFuelPage = incomeFuelRepository.findAllByEmployeeBranchIdAndIncomeTimeIsBetween(branchId, new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()), CommandUtils.simplePageable(page, size));
            Map<String, Object> response = new HashMap<>();
            response.put("fuelReports", getTotalIncomeFuel(incomeFuelPage));
            response.put("currentPage", incomeFuelPage.getNumber());
            response.put("totalItems", incomeFuelPage.getTotalElements());
            response.put("totalPages", incomeFuelPage.getTotalPages());
            return new ApiResponse<>(true, "Vaqt filial oraligidagi kirimlar!", response);
        } catch (PageSizeException e) {
            return new ApiResponse<>(false, e.getMessage());
        }
    }

    private IncomeFuelTotalDto getTotalIncomeFuel(Page<IncomeFuel> fuelReportPage) {
        List<IncomeFuelDto> incomeFuelDtoList = fuelReportPage.getContent().stream().map(incomeMapper::toDto).collect(Collectors.toList());
        double totalSumSale = 0;
        double totalSumIncome = 0;
        double totalAmount = 0;
        for (IncomeFuelDto incomeFuelDto : incomeFuelDtoList) {
            totalSumSale += (incomeFuelDto.getAmount() * incomeFuelDto.getSalePrice());
            totalSumIncome += (incomeFuelDto.getAmount() * incomeFuelDto.getIncomePrice());
            totalAmount += incomeFuelDto.getAmount();
        }
        incomeFuelDtoList.sort(Comparator.comparing(IncomeFuelDto::getIncomeTime).reversed());
        return new IncomeFuelTotalDto(totalSumIncome, totalSumSale, totalAmount, incomeFuelDtoList);
    }

    private IncomeFuelTotalDto getTotalIncomeFuel(List<IncomeFuel> fuelReportList) {
        List<IncomeFuelDto> incomeFuelDtoList = fuelReportList.stream().map(incomeMapper::toDto).collect(Collectors.toList());
        double totalSumIncome = 0;
        double totalSumSale = 0;
        double totalAmount = 0;
        for (IncomeFuelDto incomeFuelDto : incomeFuelDtoList) {
            totalSumSale += (incomeFuelDto.getAmount() * incomeFuelDto.getSalePrice());
            totalSumIncome += (incomeFuelDto.getAmount() * incomeFuelDto.getIncomePrice());
            totalAmount += incomeFuelDto.getAmount();
        }
        incomeFuelDtoList.sort(Comparator.comparing(IncomeFuelDto::getIncomeTime).reversed());
        return new IncomeFuelTotalDto(totalSumIncome, totalSumSale, totalAmount, incomeFuelDtoList);
    }


}
