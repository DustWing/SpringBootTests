package com.example.aop.controllers;


import com.example.aop.models.PaginatorRs;
import com.example.aop.services.PaginatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final PaginatorService paginatorService;

    public ApiController(
            final PaginatorService paginatorService
    ) {
        this.paginatorService = paginatorService;
    }

    @GetMapping(
            value = "/paginator",
            produces = {"application/json"}
    )
    public ResponseEntity<Object> paginator(
            @RequestParam(value = "from") int from,
            @RequestParam(value = "to") int to

    ) {

        PaginatorRs result = new PaginatorRs(
                from,
                to,
                paginatorService.pages(from,to)
        );


        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
