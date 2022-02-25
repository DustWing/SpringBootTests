package com.example.resourceserver.api.controller;

import com.example.resourceserver.api.model.User;
import com.example.resourceserver.api.model.UsersRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping(
            path = "/users",
            produces = "application/json"
    )
    public ResponseEntity<UsersRs> getUsers() {


        UsersRs usersRs = new UsersRs();

        usersRs.getData().add(
                new User()
        );

        return new ResponseEntity<>(
                usersRs,
                HttpStatus.OK
        );
    }

}
