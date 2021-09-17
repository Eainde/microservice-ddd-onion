package com.eainde.ddd.domain;

import org.immutables.value.Value;

import com.eainde.ddd.Wrapped;
import com.google.common.base.Preconditions;

import com.eainde.ddd.Wrapped;
import com.google.common.base.Preconditions;

@Value.Immutable
@Wrapped
public abstract class Country extends Domain<String> {
  private static final int USER_COUNTRY_MAX_LENGTH = 75;
  private static final String USER_COUNTRY_MAX_LENGTH_VALIDATION_FAILED_MESSAGE =
      "%s must be less than or equals %s";

  @Value.Check
  public void check() {
    final String value = value();
    Preconditions.checkState(
        value.length() <= USER_COUNTRY_MAX_LENGTH,
        USER_COUNTRY_MAX_LENGTH_VALIDATION_FAILED_MESSAGE,
        "User Country",
        USER_COUNTRY_MAX_LENGTH,
        value);
  }
}
