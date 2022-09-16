package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.FuelReport;
import com.sigma.oilstation.entity.Notification;
import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.enums.RoleType;
import com.sigma.oilstation.exceptions.PageSizeException;
import com.sigma.oilstation.mapper.NotificationMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.LimitGetDto;
import com.sigma.oilstation.payload.NotificationGetDTO;
import com.sigma.oilstation.payload.NotificationPostDTO;
import com.sigma.oilstation.repository.FuelReportRepository;
import com.sigma.oilstation.repository.NotificationRepository;
import com.sigma.oilstation.service.NotificationService;
import com.sigma.oilstation.utils.CommandUtils;
import com.sigma.oilstation.utils.PropertiesUpdate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final FuelReportRepository fuelReportRepository;

    @Value("${oil.limit.prod}")
    private long oilLimit;

    @Override
    public ApiResponse<?> create(NotificationPostDTO notificationPostDTO) {
        Notification notification = notificationMapper.toEntity(notificationPostDTO);
        notification.setSeen(false);
        notificationRepository.save(notification);
        return ApiResponse.successResponse("Saved");
    }

    @Override
    public ApiResponse<?> update(UUID id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isEmpty())
            return ApiResponse.errorResponse("Such a notification does not exist");
        Notification notification = optionalNotification.get();
        notification.setSeen(true);
        notificationRepository.save(notification);
        return ApiResponse.successResponse("Updated");
    }

    @Override
    public ApiResponse<?> get() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<NotificationGetDTO> notificationGetDTOS = null;
        if (currentUser.getRole().getType().equals(RoleType.ROLE_ADMIN)) {
            notificationGetDTOS = notificationRepository.findAllBySeenIsFalse().stream().map(notificationMapper::toGetDTO).collect(Collectors.toList());
            List<FuelReport> fuelReports = fuelReportRepository.findAllByActiveShiftIsTrue();
            for (FuelReport fuelReport : fuelReports) {
                if (fuelReport.getAmountAtStartOfShift() <= oilLimit) {
                    Notification notification = new Notification(
                            "Yoqilg'i zaxirasi uchun eslatma",
                            fuelReport.getEmployee().getBranch().getName()
                                    + " filialda " +
                                    fuelReport.getAmountAtStartOfShift() + " " + fuelReport.getFuel().getOutcomeMeasurement().getName() +
                                    " " + fuelReport.getFuel().getType() + " " + fuelReport.getFuel().getName() + " qoldi",
                            false
                    );
                    NotificationGetDTO notificationGetDTO = notificationMapper.toGetDTO(notificationRepository.save(notification));
                    notificationGetDTOS.add(notificationGetDTO);
                }
            }
        }
        return ApiResponse.successResponse(notificationGetDTOS);
    }

    @Override
    public ApiResponse<?> getOilLimit() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LimitGetDto limitGetDto = new LimitGetDto();
        if (currentUser.getRole().getType().equals(RoleType.ROLE_ADMIN)) {
            limitGetDto.setLimit(oilLimit);
        }
        return ApiResponse.successResponse(limitGetDto);
    }

    @SneakyThrows
    @Override
    public ApiResponse<?> updateLimit(Long oilLimit) {
        PropertiesUpdate.updateOilProperties(oilLimit);
        return ApiResponse.successResponse("Successfully updated limit");
    }

    @Override
    public ApiResponse<?> getAll(Integer page, Integer size) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getRole().getType().equals(RoleType.ROLE_ADMIN)) {
            Page<Notification> notificationPage;
            try {
                notificationPage = notificationRepository.findAll(CommandUtils.simplePageable(page, size));
            } catch (PageSizeException e) {
                return ApiResponse.errorResponse(e.getMessage());
            }
            Map<String, Object> response = new HashMap<>();
            response.put("notifications", notificationPage.getContent().stream().map(notificationMapper::toGetDTO).collect(Collectors.toList()));
            response.put("currentPage", notificationPage.getNumber());
            response.put("totalItems", notificationPage.getTotalElements());
            response.put("totalPages", notificationPage.getTotalPages());
            return ApiResponse.successResponse("All notifications with page", response);
        }
        return ApiResponse.errorResponse("Forbidden");
    }

}
