package com.example.authorizationserver.api.controller;


import com.example.authorizationserver.api.model.TestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @GetMapping(
            path = "/test",
            produces = "application/json"
    )
    public ResponseEntity<TestModel> test() {


        return new ResponseEntity<>(
                new TestModel(
                        UUID.randomUUID().toString(),
                        "test"
                ),
                HttpStatus.OK);

    }
}
