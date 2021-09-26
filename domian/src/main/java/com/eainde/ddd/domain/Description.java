package com.eainde.ddd.domain;

import com.eainde.ddd.Wrapped;
import com.google.common.base.Preconditions;

import org.immutables.value.Value;

@Value.Immutable
@Wrapped
public abstract class Description extends Domain<String> {
  private static final int DESCRIPTION_MAX_LENGTH = 100;
  private static final String DESCRIPTION_MAX_LENGTH_VALIDATION_FAILED_MESSAGE =
      "%s must be less than or equals %s";

  @Value.Check
  public void check() {
    final String value = value();
    Preconditions.checkState(
        value.length() <= DESCRIPTION_MAX_LENGTH,
        DESCRIPTION_MAX_LENGTH_VALIDATION_FAILED_MESSAGE,
        "DESCRIPTION",
        DESCRIPTION_MAX_LENGTH,
        value);
  }
}
