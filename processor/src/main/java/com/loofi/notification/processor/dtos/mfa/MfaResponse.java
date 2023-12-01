package com.loofi.notification.processor.dtos.mfa;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MfaResponse {
  private String mfaReferenceCode;
}
