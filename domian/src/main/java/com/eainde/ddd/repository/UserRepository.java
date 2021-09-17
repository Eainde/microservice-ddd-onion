package com.eainde.ddd.repository;

import com.eainde.ddd.aggregate.UserAggregate;

import java.util.List;

public interface UserRepository {
    List<UserAggregate> findAll();
    UserAggregate getUserById(long id);
    boolean add(UserAggregate user);
    UserAggregate update(UserAggregate user);
}
