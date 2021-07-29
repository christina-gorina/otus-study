package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Genre;

public interface GenreDao {

    Genre save(Genre genre);

    boolean delete(long id);

    Genre getById(long id);

    Genre getOrCreateByName(String name);

}
