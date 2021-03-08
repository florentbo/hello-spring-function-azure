package com.example.config;

import com.example.repo.BookRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = BookRepository.class)
public class DbConfig {
    public static final String MONGO_SERVER_URL = "";

   /* @Value("${mongo.db.url}")
    private String MONGO_DB_URL;

    @Value(("${mongo.db.port:27017}"))
    private int MONGO_DB_PORT;

    @Value("${mongo.db.name}")
    private String MONGO_DB_NAME;*/

    @Bean
    public MongoTemplate mongoTemplate() {
        ConnectionString connString = new ConnectionString(
                MONGO_SERVER_URL
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return new MongoTemplate(mongoClient, "test");
    }
}
