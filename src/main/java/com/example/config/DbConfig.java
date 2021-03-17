package com.example.config;

import static com.example.config.BusinessConfig.MONGO_SERVER_URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.repo.BookRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackageClasses = BookRepository.class)
public class DbConfig {

	//@Value("${mongo.server.url}")
	@Value("${report.export.path:sss}")
	private String mongoServerUrl;

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClientSettings settings = MongoClientSettings
													.builder()
													.applyConnectionString(new ConnectionString(MONGO_SERVER_URL))
													.retryWrites(true)
													.build();
        MongoClient mongoClient = MongoClients.create(settings);
        return new MongoTemplate(mongoClient, "test");
    }
}
