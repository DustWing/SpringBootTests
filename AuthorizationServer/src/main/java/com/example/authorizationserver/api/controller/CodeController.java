package com.example.authorizationserver.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class CodeController {

    @GetMapping("/code")
    public String errorRq(@RequestParam(value = "code") final String code) {

        return code;
    }
}
