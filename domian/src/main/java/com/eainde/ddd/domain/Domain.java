package com.eainde.ddd.domain;

import org.immutables.value.Value;

/** Base wrapper type */
public abstract class Domain<T> {
  @Value.Parameter
  public abstract T value();

  public String toString() {
    return getClass().getSimpleName() + "(" + value() + ")";
  }
}
