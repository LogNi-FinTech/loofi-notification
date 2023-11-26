package com.loofi.notification.common.entities;


import com.loofi.notification.common.enums.NotificationChannel;

import org.hibernate.envers.Audited;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Audited
@Entity
@Table(name = "notification_templates")
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NotificationTemplate extends LoofiAbstractAuditingEntity {
  @Id
  @ToString.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(name = "template_name")
  private String templateName;

  @Column(name = "description")
  private String description;

  @NotNull
  @Column(name = "notification_channel")
  @Enumerated(value = EnumType.STRING)
  private NotificationChannel notificationChannel;

  @Column(name = "subject")
  private String subject;

  @Column(name = "locale")
  private String locale;

  @NotEmpty
  @Column(name = "template")
  private String template;

  @Column(name = "is_html")
  private Boolean isHtml;

  public String cacheKey() {
    return templateName + "_" + locale;
  }
}
