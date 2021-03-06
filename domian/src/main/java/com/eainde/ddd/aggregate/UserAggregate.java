package com.eainde.ddd.aggregate;

import com.eainde.ddd.domain.Country;
import com.eainde.ddd.domain.UserAge;
import com.eainde.ddd.domain.UserId;
import com.eainde.ddd.domain.UserName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Optional;

import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public interface UserAggregate {
  UserId id();

  UserName name();

  Optional<Country> country();

  UserAge age();
}
