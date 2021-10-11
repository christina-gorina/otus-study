package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "author")
public interface AuthorDao extends JpaRepository<Author, Long> {

    Author findByName(String name);

}
