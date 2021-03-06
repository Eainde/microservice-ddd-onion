package com.eainde.ddd.service.impl;

import com.eainde.ddd.aggregate.UserAggregate;
import com.eainde.ddd.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository repository;

  UserService(final UserRepository repository) {
    this.repository = repository;
  }

  public List<UserAggregate> findAll() {
    return repository.findAll();
  }

  public UserAggregate getUserById(long id) {
    return repository.getUserById((int) id);
  }

  public boolean add(UserAggregate user) {
    return repository.add(user);
  }

  public UserAggregate update(UserAggregate user) {
    return repository.update(user);
  }
}
