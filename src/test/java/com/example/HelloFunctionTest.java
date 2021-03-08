/*
package com.example;

import com.example.model.Greeting;
import com.example.model.User;
import com.example.repo.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

//@EnableMongoRepositories(basePackageClasses = BookRepository.class)
//@DataMongoTest
//@Testcontainers
@SpringBootTest
//@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class HelloFunctionTest {


   */
/* private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    }*//*


    @Autowired
    private BookRepository bookRepository;

    @Test
    public void test() {

        Greeting result = new HelloFunction(bookRepository).apply(new User("foo"));
        //Greeting result = new HelloFunction(Mockito.mock(BookRepository.class)).apply(new User("foo"));
        //Greeting result = new HelloFunction().apply(new User("foo"));
        assertThat(result.getMessage()).isEqualTo("Hello, foo!\n");
    }

   */
/* @Test
    public void start() {
        AzureSpringBootRequestHandler<User, Greeting> handler = new AzureSpringBootRequestHandler<>(
                HelloFunction.class);
        Greeting result = handler.handleRequest(new User("foo"), null);
        handler.close();
        assertThat(result.getMessage()).isEqualTo("Hello, foo!\n");
    }*//*

}
*/
