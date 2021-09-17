package com.christinagorina.homework.controller;

import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestBookController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public Mono<List<Book>> findAll() {
        return bookService.findAll();
    }

    @GetMapping("api/books/{id}")
    public Mono<Book> findById(@PathVariable("id") String id) {
        return bookService.findById(id);
    }

    @DeleteMapping("api/books/{id}")
    public void deleteById(@PathVariable String id) {
        bookService.deleteById(id);
    }

    @PostMapping("api/books")
    public Mono<Book> save(@RequestBody Book bookInput) {
        return bookService.save(bookInput);
    }

}
