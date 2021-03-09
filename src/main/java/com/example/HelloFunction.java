package com.example;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.config.DbConfig;
import com.example.model.Greeting;
import com.example.model.User;
import com.example.repo.BookRepository;

@Component
@ComponentScan(basePackageClasses = DbConfig.class)
public class HelloFunction implements Function<User, Greeting> {

    private final BookRepository repository;

    @Autowired
    public HelloFunction(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Greeting apply(User user) {
		return new Greeting("Hello, " + repository.findByIsbn("9791034747894").getTitle() + "!\n");
        //return new Greeting("Hello, " + user.getName() + "!\n");
    }


}
