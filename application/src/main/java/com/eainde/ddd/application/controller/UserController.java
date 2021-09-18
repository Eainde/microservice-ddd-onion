package com.eainde.ddd.application.controller;

import com.eainde.ddd.aggregate.UserAggregate;
import com.eainde.ddd.application.controller.dto.UserDto;
import com.eainde.ddd.application.controller.dto.mapper.UserMapper;
import com.eainde.ddd.service.impl.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService service;
  private final UserMapper userMapperDto;

  UserController(final UserService service, final UserMapper userMapperDto) {
    this.service = service;
    this.userMapperDto = userMapperDto;
  }

  @GetMapping(value = "/", produces = "application/json")
  public ResponseEntity<List<UserDto>> findAll() {
    return new ResponseEntity<>(
        service.findAll().stream().map(userMapperDto::mapToDto).collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
    return new ResponseEntity<>(userMapperDto.mapToDto(service.getUserById(id)), HttpStatus.OK);
  }

  @PostMapping(value = "/", produces = "application/json")
  public ResponseEntity<Boolean> add(@RequestBody UserDto user) {
    return new ResponseEntity<>(service.add(userMapperDto.mapToDomain(user)), HttpStatus.OK);
  }

  @PutMapping(value = "/", produces = "application/json")
  public ResponseEntity<UserAggregate> update(@RequestBody UserDto user) {
    return new ResponseEntity<>(service.update(userMapperDto.mapToDomain(user)), HttpStatus.OK);
  }
}
