package com.loofi.notification.processor.dtos.mfa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MFACodeDto {
  private String userIdentifier;
  private String mfaCode;
  private String referenceCode;
}
