package com.eainde.ddd.entity.mapper;

import com.eainde.ddd.Mapper;
import com.eainde.ddd.aggregate.ImmutableUserAggregate;
import com.eainde.ddd.aggregate.UserAggregate;
import com.eainde.ddd.domain.*;
import com.eainde.ddd.entity.User;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserAggregate, User> {

  @Override
  public UserAggregate mapToDomain(User entity) {
    return ImmutableUserAggregate.builder()
        .id(ImmutableUserId.of((long) entity.getUserId()))
        .age(ImmutableUserAge.of(entity.getAge()))
        .name(ImmutableUserName.of(entity.getName()))
        .country(Optional.ofNullable(entity.getCountry()).map(ImmutableCountry::of))
        .build();
  }

  @Override
  public User mapToEntity(UserAggregate domain) {
    User user = new User();
    user.setUserId(domain.id().value().intValue());
    user.setAge(domain.age().value());
    user.setName(domain.name().value());
    user.setCountry(domain.country().map(Country::value).orElse(null));
    return user;
  }
}
