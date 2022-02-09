package com.example.jpa.controller;

import com.example.jpa.dbModel.User;
import com.example.jpa.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {


    private final UserService userService;

    public UserApi(
            UserService userService
    ) {
        this.userService = userService;
    }


    @GetMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    public ResponseEntity<User> findById(@PathVariable("id") final Long id) {

        return ResponseEntity.ok(
                userService.findById(id)
        );
    }

    @GetMapping(
            path = "/all",
            produces = {"application/json"}
    )
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok(
                userService.findAll()
        );
    }

    @PostMapping(
            path = "/new",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createUser(@RequestBody final User user) {

        userService.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping(
            path = "/newWithException",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createUserWithException(@RequestBody final User user) {

        userService.saveWithException(user);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createUserWithException(@PathVariable("id") final Long id) {

        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
