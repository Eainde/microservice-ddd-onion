package com.eainde.ddd.service.dto.mapper;

import com.eainde.ddd.aggregate.ImmutableOrderAggregate;
import com.eainde.ddd.aggregate.OrderAggregate;
import com.eainde.ddd.domain.ImmutableCountry;
import com.eainde.ddd.domain.ImmutableDescription;
import com.eainde.ddd.domain.ImmutableOrderId;
import com.eainde.ddd.domain.ImmutableQuantity;
import com.eainde.ddd.service.dto.inbound.OrderInboundDto;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

  public OrderAggregate mapToDomain(OrderInboundDto dto) {
    return ImmutableOrderAggregate.builder()
        .orderId(ImmutableOrderId.of((long) dto.orderId()))
        .country(ImmutableCountry.of(dto.country()))
        .description(ImmutableDescription.of(dto.description()))
        .quantity(ImmutableQuantity.of(dto.quantity()))
        .build();
  }
}
