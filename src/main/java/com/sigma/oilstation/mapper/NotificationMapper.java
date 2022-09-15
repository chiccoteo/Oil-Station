package com.sigma.oilstation.mapper;

import com.sigma.oilstation.entity.Notification;
import com.sigma.oilstation.payload.NotificationGetDTO;
import com.sigma.oilstation.payload.NotificationPostDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification toEntity(NotificationPostDTO notificationPostDTO);

    NotificationGetDTO toGetDTO(Notification notification);

}
