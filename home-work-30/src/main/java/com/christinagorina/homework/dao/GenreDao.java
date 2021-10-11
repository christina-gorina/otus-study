package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "genre")
public interface GenreDao extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

}
