package com.example.jpa.controller;

import com.example.jpa.dbModel.User;
import com.example.jpa.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApi {


    private final UserRepository userRepository;

    public UserApi(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }


    @GetMapping(
            path = "/all",
            produces = {"application/json"}
    )
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok(userRepository.findAll());
    }

}
