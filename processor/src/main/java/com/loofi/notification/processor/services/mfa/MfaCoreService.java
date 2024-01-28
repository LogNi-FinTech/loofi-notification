package com.loofi.notification.processor.services.mfa;

import com.loofi.notification.common.entities.mfa.MFACode;
import com.loofi.notification.common.errors.NotificationBusinessException;
import com.loofi.notification.common.errors.NotificationErrorCode;
import com.loofi.notification.common.repositories.mfa.MfaCodeRepository;
import com.loofi.notification.processor.dtos.mfa.MFACodeDto;
import com.loofi.notification.processor.dtos.mfa.MfaRequest;


import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.loofi.notification.common.errors.NotificationErrorCode.INVALID_MFA_REQUEST;


@Slf4j
@Service
@RequiredArgsConstructor
public class MfaCoreService {
  private final MfaCodeRepository mfaCodeRepository;
  //private final PasswordEncoder passwordEncoder;
  private final int OTP_CODE_LENGTH = 6;
  private final boolean USE_LETTER = true;
  private final boolean USE_NUMBER = true;
  private final int MFA_TIME_MINUTES = 3;

  @Transactional
  public MFACodeDto generateMfa(MfaRequest mfaRequest) {
    validateMFARequest(mfaRequest);
    return createMFACodeInternal(mfaRequest);
  }

  private MFACodeDto createMFACodeInternal(MfaRequest mfaRequest) {
    String otp = RandomStringUtils.random(OTP_CODE_LENGTH, USE_LETTER, USE_NUMBER);
    MFACode mfaCode = MFACode.builder().userIdentifier(mfaRequest.getUserIdentifier())
      .phoneNumber(mfaRequest.getPhoneNumber())
      .email(mfaRequest.getEmail())
      .generatedAt(Instant.now())
      //.hashedMFA(passwordEncoder.encode(otp))
      .used(false)
      .referenceCode(UUID.randomUUID().toString())
      .emailTemplate(mfaRequest.getEmailTemplateName())
      .smsTemplate(mfaRequest.getSmsTemplateName())
      .build();

    mfaCodeRepository.save(mfaCode);
    return MFACodeDto.builder().mfaCode(otp)
      .referenceCode(mfaCode.getReferenceCode())
      .build();
  }


  private void validateMFARequest(MfaRequest mfaRequest) {
    Instant creationTime = Instant.now().minus(MFA_TIME_MINUTES, ChronoUnit.MINUTES);
    mfaCodeRepository.findMFACodesByUserIdentifierAndUsedAndCheckCountLessThanAndCreatedDateGreaterThan(mfaRequest.getUserIdentifier(), false, 3, creationTime)
      .ifPresent((mfaCode -> {
        throw new NotificationBusinessException(INVALID_MFA_REQUEST, NotificationErrorCode.ERROR_MAP.get(INVALID_MFA_REQUEST));
      }));
  }

  @Transactional
  public boolean validateMfaCode(MFACodeDto mfaCodeDto) {
    Instant creationTime = Instant.now().minus(MFA_TIME_MINUTES, ChronoUnit.MINUTES);
    MFACode mfaCode = mfaCodeRepository.findMFACodesByUserIdentifierAndUsedAndCheckCountLessThanAndCreatedDateGreaterThan(mfaCodeDto.getUserIdentifier(), false, 3, creationTime)
      .orElseThrow(() -> new NotificationBusinessException(INVALID_MFA_REQUEST, NotificationErrorCode.ERROR_MAP.get(INVALID_MFA_REQUEST)));

//    if (!passwordEncoder.matches(mfaCode.getHashedMFA(), mfaCodeDto.getMfaCode())
//      || !mfaCode.getReferenceCode().equals(mfaCodeDto.getReferenceCode())) {
//      mfaCode.setCheckCount(mfaCode.getCheckCount() + 1);
//      return false;
//    }
//    mfaCode.setUsed(true);
    return true;
  }

}
