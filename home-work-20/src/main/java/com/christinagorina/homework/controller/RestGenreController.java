package com.christinagorina.homework.controller;

import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class RestGenreController {

    private final GenreRepository genreRepository;

    @GetMapping("/api/genres")
    public Flux<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

}
