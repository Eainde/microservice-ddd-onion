package com.eainde.ddd.application.controller.dto.mapper;

import com.eainde.ddd.aggregate.ImmutableOrderAggregate;
import com.eainde.ddd.aggregate.OrderAggregate;
import com.eainde.ddd.application.controller.dto.ImmutableOrderDto;
import com.eainde.ddd.application.controller.dto.OrderDto;
import com.eainde.ddd.domain.*;

import org.springframework.stereotype.Component;

@Component("orderMapperDto")
public class OrderMapper {
  public OrderAggregate mapToDomain(OrderDto dto) {
    return ImmutableOrderAggregate.builder()
        .orderId(ImmutableOrderId.of((long) dto.orderId()))
        .description(ImmutableDescription.of(dto.description()))
        .country(ImmutableCountry.of(dto.country()))
        .quantity(ImmutableQuantity.of(dto.quantity()))
        .build();
  }

  public OrderDto mapToDto(OrderAggregate domain) {
    return ImmutableOrderDto.builder()
        .orderId(domain.orderId().value().intValue())
        .description(domain.description().value())
        .country(domain.country().value())
        .quantity(domain.quantity().value())
        .build();
  }
}
