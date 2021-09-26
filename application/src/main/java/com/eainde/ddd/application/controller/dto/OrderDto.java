package com.eainde.ddd.application.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOrderDto.class)
public interface OrderDto {
  int orderId();

  String description();

  String country();

  int quantity();
}
