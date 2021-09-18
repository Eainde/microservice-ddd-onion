package com.eainde.ddd.application.controller.dto.mapper;

import com.eainde.ddd.aggregate.ImmutableUserAggregate;
import com.eainde.ddd.aggregate.UserAggregate;
import com.eainde.ddd.application.controller.dto.ImmutableUserDto;
import com.eainde.ddd.application.controller.dto.UserDto;
import com.eainde.ddd.domain.*;

import org.springframework.stereotype.Component;

@Component("userMapperDto")
public class UserMapper {

  public UserAggregate mapToDomain(UserDto dto) {
    return ImmutableUserAggregate.builder()
        .id(ImmutableUserId.of(dto.id()))
        .age(ImmutableUserAge.of(dto.age()))
        .country(ImmutableCountry.of(dto.country()))
        .name(ImmutableUserName.of(dto.name()))
        .build();
  }

  public UserDto mapToDto(UserAggregate domain) {
    return ImmutableUserDto.builder()
        .age(domain.age().value())
        .id(domain.id().value())
        .name(domain.name().value())
        .country(domain.country().map(Country::value).orElse(null))
        .build();
  }
}
