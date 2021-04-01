package com.eainde.ddd.aggregate;

import org.immutables.value.Value;

import com.eainde.ddd.domain.UserAge;
import com.eainde.ddd.domain.UserCountry;
import com.eainde.ddd.domain.UserId;
import com.eainde.ddd.domain.UserName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import com.eainde.ddd.domain.UserAge;
import com.eainde.ddd.domain.UserCountry;
import com.eainde.ddd.domain.UserId;
import com.eainde.ddd.domain.UserName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public interface UserAggregate {
  UserId id();

  UserName name();

  UserCountry country();

  UserAge age();
}
