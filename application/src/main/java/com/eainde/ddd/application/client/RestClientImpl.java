package com.eainde.ddd.application.client;

import com.eainde.ddd.service.client.RestClient;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.sleuth.instrument.web.mvc.TracingClientHttpRequestInterceptor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestClientImpl implements RestClient {
  private final String token;
  private final transient RestTemplate restTemplate;
  private static final Logger LOGGER = LoggerFactory.getLogger(RestClientImpl.class);
  private static final String MAKING_REQUEST = "Making {} request to : {}";

  @Autowired
  RestClientImpl(
      final RestTemplateBuilder restTemplateBuilder,
      final TracingClientHttpRequestInterceptor tracingClientHttpRequestInterceptor,
      @Value("${TOKEN}") final String token) {
    restTemplate =
        restTemplateBuilder
            .additionalInterceptors(tracingClientHttpRequestInterceptor)
            .errorHandler(new RestClientErrorHandler())
            .build();
    this.token = token;
  }

  @Override
  public <T> ResponseEntity<T> makeRequest(
      String url, String requestBody, Class<T> clazz, HttpMethod httpMethod) throws Exception {
    LOGGER.info(MAKING_REQUEST, httpMethod, url);
    var requestEntity = generateRequestEntity(requestBody);
    return restTemplate.exchange(url, httpMethod, requestEntity, clazz);
  }

  @Override
  public <T> ResponseEntity<T> makeRequest(String url, Class<T> clazz, HttpMethod httpMethod)
      throws Exception {
    LOGGER.info(MAKING_REQUEST, httpMethod, url);
    var requestEntity = new HttpEntity<String>(generateHeader());
    return restTemplate.exchange(url, httpMethod, requestEntity, clazz);
  }

  @Override
  public <T> ResponseEntity<T> makeRequest(
      String url, Map<String, String> params, Class<T> clazz, HttpMethod httpMethod)
      throws Exception {
    LOGGER.info(MAKING_REQUEST, httpMethod, url);
    var requestEntity = new HttpEntity<String>(generateHeader());
    var uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(params).toUri();
    uri = UriComponentsBuilder.fromUri(uri).build().toUri();
    return restTemplate.exchange(uri, httpMethod, requestEntity, clazz);
  }

  @Override
  public <T> ResponseEntity<T> makeRequest(
      String url,
      String requestBody,
      Map<String, String> params,
      Class<T> clazz,
      HttpMethod httpMethod)
      throws Exception {
    LOGGER.info(MAKING_REQUEST, httpMethod, url);
    var requestEntity = generateRequestEntity(requestBody);
    var uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(params).toUri();
    uri = UriComponentsBuilder.fromUri(uri).build().toUri();
    return restTemplate.exchange(uri, httpMethod, requestEntity, clazz);
  }

  private HttpEntity<String> generateRequestEntity(String requestBody) {
    var httpHeaders = generateHeader();
    return new HttpEntity<>(requestBody, httpHeaders);
  }

  private HttpHeaders generateHeader() {
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("TOKEN", token);
    return headers;
  }
}
