package com.eainde.ddd.application.controller;

import com.eainde.ddd.aggregate.UserAggregate;
import com.eainde.ddd.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    UserController(final UserService service){
        this.service=service;
    }

    @GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<List<UserAggregate>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<UserAggregate> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Boolean> add(@RequestBody UserAggregate user){
        return new ResponseEntity<>(service.add(user), HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UserAggregate> update(@RequestBody UserAggregate user){
        return new ResponseEntity<>(service.update(user), HttpStatus.OK);
    }
}
