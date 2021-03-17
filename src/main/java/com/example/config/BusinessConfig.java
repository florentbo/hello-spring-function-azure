package com.example.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.business.Manager;
import com.example.connector.FtpConnector;
import com.example.model.Greeting;
import com.example.model.User;
import com.example.repo.Book;
import com.example.repo.BookRepository;

@Configuration
public class BusinessConfig {

	public static final String SERVER = "";
	public static final String USER = "";
	public static final String PASSWORD = "";
	public static final String MONGO_SERVER_URL = "";

	@Value("${report.export.path:sss}")
	private String ftpHostname;

	@Value("${report.export.path:sss}")
	private String ftpUsername;

	@Value("${report.export.path:sss}")
	private String ftpPassword;

	private final BookRepository repository;

	@Autowired
	public BusinessConfig(BookRepository repository) {
		this.repository = repository;
	}

	@Bean
	public Manager manager() {
    	return new Manager(repository, ftpClient());
	}

	@Bean
	public FtpConnector ftpClient() {
		return new FtpConnector(SERVER, 21, USER, PASSWORD);
	}

	/*@Bean
	public Function<User, Greeting> timedTrigger() {
		final Book byIsbn = manager().findByIsbn("9782413003526");
		return user -> new Greeting("Welcome, " + byIsbn.getTitle());
	}*/
}
