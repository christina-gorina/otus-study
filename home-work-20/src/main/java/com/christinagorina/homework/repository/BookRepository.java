package com.christinagorina.homework.repository;

import com.christinagorina.homework.domain.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
