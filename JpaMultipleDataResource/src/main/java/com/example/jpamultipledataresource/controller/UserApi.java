package com.example.jpamultipledataresource.controller;

import com.example.jpamultipledataresource.dbModel.db1.User;
import com.example.jpamultipledataresource.repositories.db1.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
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
