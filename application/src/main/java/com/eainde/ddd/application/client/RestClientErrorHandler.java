package com.eainde.ddd.application.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.micrometer.core.instrument.util.IOUtils;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestClientErrorHandler implements ResponseErrorHandler {
  private final ResponseErrorHandler errorHandler;

  RestClientErrorHandler() {
    this.errorHandler = new DefaultResponseErrorHandler();
  }

  @Override
  public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
    return errorHandler.hasError(clientHttpResponse);
  }

  @Override
  public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
    var response = IOUtils.toString(clientHttpResponse.getBody());
    XmlMapper xmlMapper = new XmlMapper();
    ObjectMapper objectMapper = new ObjectMapper();
    ExceptionResponseDto exceptionResponseDto;
    if (response.contains("<")) {
      exceptionResponseDto = xmlMapper.readValue(response, ImmutableExceptionResponseDto.class);
      throw new RuntimeException(exceptionResponseDto.description());
    }
    if (response.contains("{")) {
      exceptionResponseDto = objectMapper.readValue(response, ImmutableExceptionResponseDto.class);
      throw new RuntimeException(exceptionResponseDto.description());
    }
    throw new RuntimeException(response);
  }
}
