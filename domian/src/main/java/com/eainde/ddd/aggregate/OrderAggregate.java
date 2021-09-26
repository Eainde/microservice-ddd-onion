package com.eainde.ddd.aggregate;

import com.eainde.ddd.domain.Country;
import com.eainde.ddd.domain.Description;
import com.eainde.ddd.domain.OrderId;
import com.eainde.ddd.domain.Quantity;

import org.immutables.value.Value;

@Value.Immutable
public interface OrderAggregate {
  OrderId orderId();

  Description description();

  Country country();

  Quantity quantity();
}
