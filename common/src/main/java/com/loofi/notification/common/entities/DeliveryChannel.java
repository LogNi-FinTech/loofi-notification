package com.loofi.notification.common.entities;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Audited
@Entity
@Table(name = "delivery_channels")
@ToString(onlyExplicitlyIncluded = true)
public class DeliveryChannel extends LoofiAbstractAuditingEntity {
  @Id
  @ToString.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "channel_url")
  private String channelUrl;

  @Column(name = "params")
  private String params;

  @Column(name = "method")
  private String method;

}
