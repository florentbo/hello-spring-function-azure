package com.example.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class User {

    public User() {
    }

    public User(String name) {
		log.info("202003150919-User created with name: {}", name);
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
