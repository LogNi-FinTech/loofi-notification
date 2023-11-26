package com.loofi.notification.common.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class LoofiAbstractAuditingEntity {
  private static final long serialVersionUID = 1L;

  @JoinColumn(name = "created_by")
  private String createdBy;

  @CreationTimestamp
  @Column(name = "created_date", nullable = false,updatable = false)
  private Instant createdDate;

  @JoinColumn(name = "last_modified_by")
  private String lastModifiedBy;

  @UpdateTimestamp
  @Column(name = "last_modified_date")
  private Instant lastModifiedDate;
}