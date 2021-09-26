package com.eainde.ddd.service.impl;

import com.eainde.ddd.aggregate.OrderAggregate;
import com.eainde.ddd.service.client.OrderRestClient;
import com.eainde.ddd.service.dto.mapper.OrderMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRestClient orderRestClient;
  private final OrderMapper orderMapper;

  OrderService(final OrderRestClient orderRestClient, final OrderMapper orderMapper) {
    this.orderRestClient = orderRestClient;
    this.orderMapper = orderMapper;
  }

  public List<OrderAggregate> getOrders() {
    return orderRestClient
        .retrieveOrders()
        .stream()
        .map(orderMapper::mapToDomain)
        .collect(Collectors.toList());
  }

  public OrderAggregate getOrder(int id) {
    return orderMapper.mapToDomain(orderRestClient.retrieveOrder(id));
  }
}
