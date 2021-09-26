package com.eainde.ddd.service.client;

import com.eainde.ddd.exception.ExternalApiException;
import com.eainde.ddd.service.dto.inbound.OrderInboundDto;

import java.util.*;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderRestClient {
  private final RestClient restClient;
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestClient.class);
  private final String orderServiceUrl;

  OrderRestClient(
      final RestClient restClient, @Value("${ORDER_SERVICE_URL}") final String orderServiceUrl) {
    this.restClient = restClient;
    this.orderServiceUrl = orderServiceUrl;
  }

  public List<OrderInboundDto> retrieveOrders() {
    try {
      final ResponseEntity<OrderInboundDto[]> responseEntity =
          restClient.makeRequest(orderServiceUrl, OrderInboundDto[].class, HttpMethod.GET);
      return Arrays.asList(
          Optional.ofNullable(responseEntity.getBody()).orElseThrow(NoResultException::new));
    } catch (Exception e) {
      LOGGER.error("Order call failed :", e);
      throw new ExternalApiException("Order call failed");
    }
  }

  public OrderInboundDto retrieveOrder(int id) {
    Map<String, String> params = new HashMap<>();
    params.put("id", String.valueOf(id));
    try {
      final ResponseEntity<OrderInboundDto> responseEntity =
          restClient.makeRequest(
              appendIdParamToUrl(), params, OrderInboundDto.class, HttpMethod.GET);
      return Optional.ofNullable(responseEntity.getBody()).orElseThrow(NoResultException::new);
    } catch (Exception e) {
      LOGGER.error("Order call failed :", e);
      throw new ExternalApiException("Order call failed");
    }
  }

  private String appendIdParamToUrl() {
    return orderServiceUrl + "{id}";
  }
}
