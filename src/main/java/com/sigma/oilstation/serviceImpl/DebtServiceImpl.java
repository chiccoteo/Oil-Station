package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.Debt;
import com.sigma.oilstation.entity.Supplier;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.DebtGetDto;
import com.sigma.oilstation.payload.DebtPostDto;
import com.sigma.oilstation.payload.DebtUpdateDto;
import com.sigma.oilstation.repository.DebtRepository;
import com.sigma.oilstation.repository.SupplierRepository;
import com.sigma.oilstation.repository.UserRepository;
import com.sigma.oilstation.service.DebtService;
import com.sigma.oilstation.utils.CommandUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    final private DebtRepository debtRepository;

    final private UserRepository userRepository;

    final private SupplierRepository supplierRepository;

    @Override
    public ApiResponse<?> addDebt(DebtPostDto debtPostDto) {
        if (debtPostDto.getAmount() > 0) {
            if (debtPostDto.getBorrower().length() > 0 && debtPostDto.getLenderOrBorrowerId() != null) {
                Optional<User> optionalLender = userRepository.findById(debtPostDto.getLenderOrBorrowerId());
                if (optionalLender.isEmpty()) {
                    return ApiResponse.errorResponse("Such a lender does not exist");
                } else {
                    Debt debt = new Debt();
                    debt.setBorrower(debtPostDto.getBorrower());
                    debt.setAmount(debtPostDto.getAmount());
                    User user = optionalLender.get();
                    debt.setLenderOrBorrower(user);
                    debt.setLender(null);
                    debt.setGivenTime(debtPostDto.getGivenTime());
                    debt.setReturnTime(debtPostDto.getReturnTime());
                    debt.setGiven(false);
                    debtRepository.save(debt);
                    return ApiResponse.successResponse("Successfully added");
                }
            } else if (debtPostDto.getBorrower() != null && debtPostDto.getLenderId() != null) {
                Optional<Supplier> optionalLender = supplierRepository.findById(debtPostDto.getLenderId());
                Optional<User> optionalBorrower = userRepository.findById(debtPostDto.getLenderOrBorrowerId());
                if (optionalBorrower.isEmpty()) {
                    return ApiResponse.errorResponse("Such a borrower does not exist");
                } else if (optionalLender.isEmpty()) {
                    return ApiResponse.errorResponse("Such a lender does not exist");
                } else {
                    Debt debt = new Debt();
                    debt.setBorrower(debtPostDto.getBorrower());
                    debt.setAmount(debtPostDto.getAmount());
                    User user = optionalBorrower.get();
                    debt.setLenderOrBorrower(user);
                    System.out.println(user);
                    Supplier supplier = optionalLender.get();
                    System.out.println(supplier);
                    debt.setLender(supplier);
                    debt.setGivenTime(debtPostDto.getGivenTime());
                    debt.setReturnTime(debtPostDto.getReturnTime());
                    debt.setGiven(false);
                    debtRepository.save(debt);
                    return ApiResponse.successResponse("Successfully added supplier is lender");
                }
            } else {
                return ApiResponse.errorResponse("Lender or Borrower not included");
            }
        }
        return ApiResponse.errorResponse("Amount not included");
    }

    @Override
    public ApiResponse<?> getByIdDebt(UUID id) {
        Optional<Debt> optionalDebt = debtRepository.findById(id);
        if (optionalDebt.isEmpty()){
            return ApiResponse.errorResponse("Such a debt does not exist");
        }
        Debt debt = optionalDebt.get();
        DebtGetDto debtGetDto = new DebtGetDto();
        debtGetDto.setId(debt.getId());
        debtGetDto.setBorrower(debt.getBorrower());
        debtGetDto.setAmount(debt.getAmount());
        debtGetDto.setLenderOrBorrowerId(debt.getLenderOrBorrower().getId());
        debtGetDto.setLenderId(debt.getLender().getId());
        debtGetDto.setGivenTime(debt.getGivenTime());
        debtGetDto.setReturnTime(debt.getReturnTime());
        debtGetDto.setGiven(debt.isGiven());
        return ApiResponse.successResponse("Success", debtGetDto);
    }

    @Override
    public ApiResponse<?> getAllDebtPageable(Integer page, Integer size) {
        Page<Debt> debtPage;
        try {
            debtPage = debtRepository.findAll(CommandUtils.simplePageable(page,size));
        }catch (PageSizeException e){
            return ApiResponse.errorResponse(e.getMessage());
        }
        List<Debt> debtList = debtPage.getContent();
        List<DebtGetDto> debtGetDtoList = new LinkedList<>();

        for (Debt debt : debtList) {
            DebtGetDto debtGetDto = new DebtGetDto();
            debtGetDto.setId(debt.getId());
            debtGetDto.setBorrower(debt.getBorrower());
            debtGetDto.setAmount(debt.getAmount());
            debtGetDto.setLenderOrBorrowerId(debt.getLenderOrBorrower().getId());
            if (debt.getLender() != null) {
                debtGetDto.setLenderId(debt.getLender().getId());
            }else {
                debtGetDto.setLenderId(null);
            }
            debtGetDto.setGivenTime(debt.getGivenTime());
            debtGetDto.setReturnTime(debt.getReturnTime());
            debtGetDto.setGiven(debt.isGiven());
            debtGetDtoList.add(debtGetDto);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("debt", debtGetDtoList);
        response.put("currentPage", debtPage.getNumber());
        response.put("totalItems", debtPage.getTotalElements());
        response.put("totalPages", debtPage.getTotalPages());
        return ApiResponse.successResponse("All fuel with page", response);
    }

    @Override
    public ApiResponse<?> getAllDebt() {
        List<DebtGetDto> debtGetDtoList = new LinkedList<>();
        List<Debt> debtList = debtRepository.findAll();

        for (Debt debt : debtList) {
            DebtGetDto debtGetDto = new DebtGetDto();
            debtGetDto.setId(debt.getId());
            debtGetDto.setBorrower(debt.getBorrower());
            debtGetDto.setAmount(debt.getAmount());
            debtGetDto.setLenderOrBorrowerId(debt.getLenderOrBorrower().getId());
            if (debt.getLender() != null) {
                debtGetDto.setLenderId(debt.getLender().getId());
            }else {
                debtGetDto.setLenderId(null);
            }
            debtGetDto.setGivenTime(debt.getGivenTime());
            debtGetDto.setReturnTime(debt.getReturnTime());
            debtGetDto.setGiven(debt.isGiven());
            System.out.println(debt);
            System.out.println(debtGetDto);
            debtGetDtoList.add(debtGetDto);
        }
        return ApiResponse.successResponse("All debt", debtGetDtoList);
    }

    @Override
    public ApiResponse<?> updateDebt(UUID id, DebtUpdateDto debtUpdateDto) {
        Optional<Debt> optionalDebt = debtRepository.findById(id);
        if (optionalDebt.isEmpty()){
            return ApiResponse.errorResponse("Such a debt does not exist");
        }
        Debt debt = optionalDebt.get();
        if (debtUpdateDto.getAmount() > 0) {
            if (debtUpdateDto.getBorrower() != null && debtUpdateDto.getLenderOrBorrowerId() != null) {
                Optional<User> optionalLender = userRepository.findById(debtUpdateDto.getLenderOrBorrowerId());
                if (optionalLender.isEmpty()) {
                    return ApiResponse.errorResponse("Such a lender does not exist");
                } else {

                    debt.setBorrower(debtUpdateDto.getBorrower());
                    debt.setAmount(debtUpdateDto.getAmount());
                    debt.setLenderOrBorrower(optionalLender.get());
                    debt.setLender(null);
                    debt.setGivenTime(debtUpdateDto.getGivenTime());
                    debt.setReturnTime(debtUpdateDto.getReturnTime());
                    debt.setGiven(debtUpdateDto.isGiven());
                    debtRepository.save(debt);
                    return ApiResponse.successResponse("Successfully updated");
                }
            } else if (debtUpdateDto.getBorrower() != null && debtUpdateDto.getLenderId() != null) {
                Optional<Supplier> optionalLender = supplierRepository.findById(debtUpdateDto.getLenderId());
                Optional<User> optionalBorrower = userRepository.findById(debtUpdateDto.getLenderOrBorrowerId());
                if (optionalBorrower.isEmpty()) {
                    return ApiResponse.errorResponse("Such a borrower does not exist");
                } else if (optionalLender.isEmpty()) {
                    return ApiResponse.errorResponse("Such a lender does not exist");
                } else {
                    debt.setBorrower(null);
                    debt.setAmount(debtUpdateDto.getAmount());
                    debt.setLenderOrBorrower(optionalBorrower.get());
                    debt.setLender(optionalLender.get());
                    debt.setGivenTime(debtUpdateDto.getGivenTime());
                    debt.setReturnTime(debtUpdateDto.getReturnTime());
                    debt.setGiven(debtUpdateDto.isGiven());
                    debtRepository.save(debt);
                    return ApiResponse.successResponse("Successfully updated");
                }
            }  else {
                return ApiResponse.errorResponse("Lender or Borrower not included");
            }
        }
        return ApiResponse.errorResponse("Amount not included");
    }

    @Override
    public ApiResponse<?> deleteDebt(UUID id) {
        Optional<Debt> optionalDebt = debtRepository.findById(id);
        if (optionalDebt.isEmpty()){
            return ApiResponse.errorResponse("Such a debt does not exist");
        }
        Debt debt = optionalDebt.get();
        debt.setGiven(false);
        debtRepository.save(debt);
        return ApiResponse.successResponse("Successfully deleted");
    }
}
