package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByName(String name);
}
