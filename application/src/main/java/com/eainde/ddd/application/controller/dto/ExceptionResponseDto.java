package com.eainde.ddd.application.controller.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(as = ImmutableExceptionResponseDto.class)
public interface ExceptionResponseDto {
  int code();

  String description();
}
