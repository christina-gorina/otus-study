package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAll();
}
