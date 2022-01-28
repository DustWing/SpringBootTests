package com.example.aop.models;

import java.util.List;

public record PaginatorRs(
        int from,
        int to,
        List<String> pages
) {

}
