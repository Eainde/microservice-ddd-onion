package com.eainde.ddd.domain;

import com.eainde.ddd.Wrapped;
import com.google.common.base.Preconditions;

import org.immutables.value.Value;

@Value.Immutable
@Wrapped
public abstract class UserId extends Domain<Long> {
  private static final String USER_ID_VALIDATION_FAILED_MESSAGE = "user id cannot be 0";

  @Value.Check
  public void check() {
    final long value = value();
    Preconditions.checkState(value != 0, USER_ID_VALIDATION_FAILED_MESSAGE);
  }
}
