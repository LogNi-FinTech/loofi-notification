package com.loofi.notification.processor.controllers.mfa;


import com.loofi.notification.processor.dtos.mfa.MFACodeDto;
import com.loofi.notification.processor.dtos.mfa.MfaRequest;
import com.loofi.notification.processor.dtos.mfa.MfaResponse;
import com.loofi.notification.processor.services.mfa.MfaWrapperService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MFAController {

  private final MfaWrapperService mfaWrapperService;

  @PostMapping("/v1/api/mfa/generate")
  public MfaResponse generateMFA(@Valid @RequestBody MfaRequest mfaRequest) {
    return mfaWrapperService.generateMfa(mfaRequest);
  }

  @PostMapping("/v1/api/mfa/validate")
  public void validateMFA(
    @Valid @RequestBody MFACodeDto validateMFACodeRequest) {
    mfaWrapperService.validateMfa(validateMFACodeRequest);
  }
}
