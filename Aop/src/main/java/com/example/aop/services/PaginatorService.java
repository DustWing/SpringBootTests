package com.example.aop.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaginatorService {

    private static final List<String> cachedList = Arrays.asList(
            "1",
            "2",
            "3",
            "4"
    );


    public List<String> pages(
            int from,
            int to
    ) {


        if (from > to) {
            throw new RuntimeException("from > to");
        }

        if (from > cachedList.size() || to > cachedList.size()) {
            throw new RuntimeException("To many pages!");
        }

        return cachedList.subList(from - 1, to);


    }

}
