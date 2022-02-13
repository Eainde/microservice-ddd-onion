package com.eainde.ddd.application.controller;

import com.eainde.ddd.application.controller.dto.OrderDto;
import com.eainde.ddd.application.controller.dto.mapper.OrderMapper;
import com.eainde.ddd.service.impl.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api("Order Controller")
public class OrderController {
  private final OrderService service;
  private final OrderMapper orderMapperDto;

  OrderController(final OrderService service, final OrderMapper orderMapperDto) {
    this.service = service;
    this.orderMapperDto = orderMapperDto;
  }

  @ApiOperation(value = "Get all orders", response = List.class)
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<OrderDto>> findAll() {
    return new ResponseEntity<>(
        service.getOrders().stream().map(orderMapperDto::mapToDto).collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @ApiOperation(value = "Get order by id", response = OrderDto.class)
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") int id) {
    return new ResponseEntity<>(orderMapperDto.mapToDto(service.getOrder(id)), HttpStatus.OK);
  }
}
