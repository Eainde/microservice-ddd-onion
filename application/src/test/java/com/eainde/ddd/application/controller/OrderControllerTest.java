package com.eainde.ddd.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eainde.ddd.aggregate.ImmutableOrderAggregate;
import com.eainde.ddd.aggregate.OrderAggregate;
import com.eainde.ddd.application.controller.dto.mapper.OrderMapper;
import com.eainde.ddd.domain.ImmutableCountry;
import com.eainde.ddd.domain.ImmutableDescription;
import com.eainde.ddd.domain.ImmutableOrderId;
import com.eainde.ddd.domain.ImmutableQuantity;
import com.eainde.ddd.service.impl.OrderService;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = OrderController.class)
@TestPropertySource(locations = {"classpath:unitTest.properties"})
class OrderControllerTest {
  private MockMvc mockMvc;
  @Autowired private WebApplicationContext context;
  @MockBean private OrderService service;
  @MockBean private OrderMapper orderMapperDto;

  private OrderAggregate orderAggregate;
  private List<OrderAggregate> orderAggregateList;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    orderAggregate =
        ImmutableOrderAggregate.builder()
            .orderId(ImmutableOrderId.of(1L))
            .country(ImmutableCountry.of("India"))
            .description(ImmutableDescription.of("Ko"))
            .quantity(ImmutableQuantity.of(3))
            .build();
    orderAggregateList = new ArrayList<>();
    orderAggregateList.add(orderAggregate);
  }

  @Test
  void findAll() throws Exception {
    when(service.getOrders()).thenReturn(orderAggregateList);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/order/"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void getOrderById() throws Exception {
    when(service.getOrder(1)).thenReturn(orderAggregate);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/order/1"))
        .andExpect(status().isOk())
        //    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
        .andDo(MockMvcResultHandlers.print());
  }
}
