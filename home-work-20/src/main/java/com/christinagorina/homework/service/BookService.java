package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Book;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookService {

    Mono<Book> save(Book createdBook);

    Mono<Book> findById(String id);

    void deleteById(String id);

    Mono<List<Book>> findAll();
}
