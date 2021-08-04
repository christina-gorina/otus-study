package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findByName(String name);
}
