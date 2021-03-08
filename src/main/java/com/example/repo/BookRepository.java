package com.example.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    Book findByIsbn(String isbn);

    List<Book> findByShopfifyId(String id);
}