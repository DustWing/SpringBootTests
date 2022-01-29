package com.example.jpamultipledataresource.controller;

import com.example.jpamultipledataresource.dbModel.db2.Password;
import com.example.jpamultipledataresource.repositories.db2.PasswordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/password")
public class PasswordApi {


    private final PasswordRepository passwordRepository;

    public PasswordApi(
            PasswordRepository passwordRepository
    ) {
        this.passwordRepository = passwordRepository;
    }


    @GetMapping(
            path = "/all",
            produces = {"application/json"}
    )
    public ResponseEntity<List<Password>> getPasswords() {

        return ResponseEntity.ok(passwordRepository.findAll());
    }

}
