package com.loofi.notification.common.repositories;

import com.loofi.notification.common.entities.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
