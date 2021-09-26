package com.eainde.ddd.service.client;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface RestClient {
  <T> ResponseEntity<T> makeRequest(
      String url, String requestBody, Class<T> clazz, HttpMethod httpMethod) throws Exception;

  <T> ResponseEntity<T> makeRequest(String url, Class<T> clazz, HttpMethod httpMethod)
      throws Exception;

  <T> ResponseEntity<T> makeRequest(
      String url, Map<String, String> params, Class<T> clazz, HttpMethod httpMethod)
      throws Exception;

  <T> ResponseEntity<T> makeRequest(
      String url,
      String requestBody,
      Map<String, String> params,
      Class<T> clazz,
      HttpMethod httpMethod)
      throws Exception;
}
