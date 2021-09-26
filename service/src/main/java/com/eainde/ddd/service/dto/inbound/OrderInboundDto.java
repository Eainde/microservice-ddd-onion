package com.eainde.ddd.service.dto.inbound;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableOrderInboundDto.class)
public interface OrderInboundDto {
  int orderId();

  String description();

  String country();

  int quantity();
}
