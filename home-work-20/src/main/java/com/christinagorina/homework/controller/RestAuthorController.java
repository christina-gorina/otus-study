package com.christinagorina.homework.controller;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class RestAuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("/api/authors")
    public Flux<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

}
