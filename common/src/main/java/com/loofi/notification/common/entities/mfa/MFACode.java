package com.loofi.notification.common.entities.mfa;

import com.loofi.notification.common.entities.LoofiAbstractAuditingEntity;

import org.hibernate.envers.Audited;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mfa_codes")
@Getter
@Setter
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MFACode extends LoofiAbstractAuditingEntity {
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mfa_code_id_seq")
  @Id
  @SequenceGenerator(
    name = "mfa_code_id_seq",
    allocationSize = 1,
    sequenceName = "mfa_code_id_seq")
  private Long id;

  @Column(name = "hashed_mfa")
  private String hashedMFA;

  private String referenceCode;

  private Instant generatedAt;

  @Column(name = "is_used")
  private boolean used;

  private int checkCount;

  private String userIdentifier;

  private String email;

  private String phoneNumber;

  private boolean viaEmail;

  private boolean viaSms;

  private boolean viaPushNotification;

  private String emailTemplate;

  private String smsTemplate;

  private String pushNotificationTemplate;

  private String locale;
}
