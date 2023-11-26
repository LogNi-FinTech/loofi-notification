package com.loofi.notification.common.entities;

import com.loofi.notification.common.enums.DeliveryStatus;
import com.loofi.notification.common.enums.NotificationChannel;
import com.loofi.notification.common.enums.NotificationPriority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "notifications")
@ToString(onlyExplicitlyIncluded = true)
public class Notification extends LoofiAbstractAuditingEntity  {

  @Id
  @ToString.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // todo encrypt sensitive data otp, other
  @Column(name = "body")
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "template_id")
  private NotificationTemplate notificationTemplate;

  @Column(name = "notification_channel")
  @Enumerated(value = EnumType.STRING)
  private NotificationChannel notificationChannel;


  @Column(name = "delivery_status")
  @Enumerated(value = EnumType.STRING)
  private DeliveryStatus deliveryStatus;

  @Column(name = "error")
  private String error;

  @Column(name = "retry_count")
  private Integer retryCount = 0;

  @ManyToOne
  @JoinColumn (name = "delivery_channel_id")
  private DeliveryChannel deliveryChannel;

  @Column(name = "priority")
  @Enumerated(value = EnumType.STRING)
  private NotificationPriority priority;
}
