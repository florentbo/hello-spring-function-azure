package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.config.DbConfig;
import com.example.model.Greeting;
import com.example.model.User;
import com.example.repo.Book;
import com.example.repo.BookRepository;

@ContextConfiguration(classes = DbConfig.class)
@DataMongoTest
public class HelloFunctionTest {

    @Test
    public void test() {
		final BookRepository mock = mock(BookRepository.class);
		when(mock.findByIsbn("9791034747894")).thenReturn(Book.builder().title("foo").build());
		Greeting result = new HelloFunction(mock).apply(new User("foo"));
		assertThat(result.getMessage()).isEqualTo("Hello, foo!\n");
	}
}
