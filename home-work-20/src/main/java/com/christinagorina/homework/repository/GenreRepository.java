package com.christinagorina.homework.repository;

import com.christinagorina.homework.domain.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

    Mono<Genre> findByName(String name);

}
