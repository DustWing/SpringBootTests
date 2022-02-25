package com.example.resourceserver.api.model;

import java.util.ArrayList;
import java.util.List;

public class UsersRs {

    private List<User> data = new ArrayList<>();


    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
