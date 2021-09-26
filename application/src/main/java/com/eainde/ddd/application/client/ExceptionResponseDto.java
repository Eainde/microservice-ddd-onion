package com.eainde.ddd.application.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableExceptionResponseDto.class)
@JsonDeserialize(as = ImmutableExceptionResponseDto.class)
public interface ExceptionResponseDto {
  int code();

  String description();
}
