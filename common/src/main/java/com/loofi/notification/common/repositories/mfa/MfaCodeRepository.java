package com.loofi.notification.common.repositories.mfa;



import com.loofi.notification.common.entities.mfa.MFACode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface MfaCodeRepository extends JpaRepository<MFACode, Long> {
  Optional<MFACode> findMFACodesByUserIdentifierAndUsedAndCheckCountLessThanAndCreatedDateGreaterThan(String userIdentifier, boolean isUsed, int counter, Instant creationTime);
}
