package com.loofi.notification.processor.services.mfa;

import com.loofi.notification.common.errors.NotificationBusinessException;
import com.loofi.notification.common.errors.NotificationErrorCode;
import com.loofi.notification.processor.dtos.mfa.MFACodeDto;
import com.loofi.notification.processor.dtos.mfa.MfaRequest;
import com.loofi.notification.processor.dtos.mfa.MfaResponse;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import static com.loofi.notification.common.errors.NotificationErrorCode.INVALID_MFA_REQUEST;

@Service
@RequiredArgsConstructor
public class MfaWrapperService {
  private final MfaCoreService mfaCoreService;

  public MfaResponse generateMfa(MfaRequest mfaRequest) {
    MFACodeDto mfaCodeDto = mfaCoreService.generateMfa(mfaRequest);
    //todo generate notification from template
    return MfaResponse.builder().mfaReferenceCode(mfaCodeDto.getReferenceCode()).build();
  }

  public void validateMfa(MFACodeDto mfaCodeDto) {
    if (!mfaCoreService.validateMfaCode(mfaCodeDto)) {
      throw new NotificationBusinessException(INVALID_MFA_REQUEST, NotificationErrorCode.ERROR_MAP.get(INVALID_MFA_REQUEST));
    }
  }
}

