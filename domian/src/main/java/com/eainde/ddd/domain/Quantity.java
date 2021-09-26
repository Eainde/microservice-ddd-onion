package com.eainde.ddd.domain;

import com.eainde.ddd.Wrapped;
import com.google.common.base.Preconditions;

import org.immutables.value.Value;

@Value.Immutable
@Wrapped
public abstract class Quantity extends Domain<Integer> {
  private static final String QUANTITY_VALIDATION_FAILED_MESSAGE = "quantity cannot be 0";

  @Value.Check
  public void check() {
    final long value = value();
    Preconditions.checkState(value != 0, QUANTITY_VALIDATION_FAILED_MESSAGE);
  }
}
