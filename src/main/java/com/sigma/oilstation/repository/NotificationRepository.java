package com.sigma.oilstation.repository;

import com.sigma.oilstation.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findAllBySeenIsFalse();

}
