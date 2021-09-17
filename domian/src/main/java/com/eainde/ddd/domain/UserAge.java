package com.eainde.ddd.domain;

import org.immutables.value.Value;

import com.eainde.ddd.Wrapped;
import com.google.common.base.Preconditions;

import com.eainde.ddd.Wrapped;
import com.google.common.base.Preconditions;

@Value.Immutable
@Wrapped
public abstract class UserAge extends Domain<Integer> {
  private static final String USER_AGE_VALIDATION_FAILED_MESSAGE = "user age cannot be 0";

  @Value.Check
  public void check() {
    final int value = value();
    Preconditions.checkState(value != 0, USER_AGE_VALIDATION_FAILED_MESSAGE);
  }
}
