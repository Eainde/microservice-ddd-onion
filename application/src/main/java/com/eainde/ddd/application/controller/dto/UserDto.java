package com.eainde.ddd.application.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.annotation.Nullable;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableUserDto.class)
@JsonDeserialize(as = ImmutableUserDto.class)
public interface UserDto {
  long id();

  String name();

  @Nullable
  String country();

  int age();
}
