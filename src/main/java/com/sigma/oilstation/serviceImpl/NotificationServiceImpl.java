package com.sigma.oilstation.serviceImpl;

import com.sigma.oilstation.entity.FuelReport;
import com.sigma.oilstation.entity.Notification;
import com.sigma.oilstation.mapper.NotificationMapper;
import com.sigma.oilstation.payload.ApiResponse;
import com.sigma.oilstation.payload.NotificationGetDTO;
import com.sigma.oilstation.payload.NotificationPostDTO;
import com.sigma.oilstation.repository.FuelReportRepository;
import com.sigma.oilstation.repository.NotificationRepository;
import com.sigma.oilstation.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final FuelReportRepository fuelReportRepository;

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
        List<NotificationGetDTO> notificationGetDTOS = notificationRepository.findAllBySeenIsFalse().stream().map(notificationMapper::toGetDTO).collect(Collectors.toList());
        List<FuelReport> fuelReports = fuelReportRepository.findAllByActiveShiftIsTrue();
        for (FuelReport fuelReport : fuelReports) {
            Notification notification = new Notification(fuelReport.getEmployee().getBranch().getName()
                    + " filialda " +
                    fuelReport.getAmountAtStartOfShift() + " " + fuelReport.getFuel().getOutcomeMeasurement().getName() +
                    " " + fuelReport.getFuel().getName() + " qoldi",
                    false
            );
            NotificationGetDTO notificationGetDTO = notificationMapper.toGetDTO(notificationRepository.save(notification));
            notificationGetDTOS.add(notificationGetDTO);
        }
        return ApiResponse.successResponse(notificationGetDTOS);
    }

}
