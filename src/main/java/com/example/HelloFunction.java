package com.example;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.business.Manager;
import com.example.config.BusinessConfig;
import com.example.config.DbConfig;
import com.example.model.Greeting;
import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ComponentScan(basePackageClasses = {DbConfig.class, BusinessConfig.class})
public class HelloFunction implements Function<User, Greeting> {

	private final Manager manager;

	@Autowired
	public HelloFunction(Manager manager) {
		this.manager = manager;
	}

	@Override
	public Greeting apply(User user) {
		//final String folderPath = "/home/florent/development/personal-projects/jaune";

		final String title = manager.findByIsbn(user.getName()).getTitle();
		final String fileName = manager.getFileName();
		log.info("202003150919-function title: {}  file name: {} ", title, fileName);
		return new Greeting("Hello, " + title + fileName + "!\n");
		//return new Greeting("Hello, " + user.getName() + "!\n");
	}
}

