package com.christinagorina.moddbhomework.dao;

import com.christinagorina.moddbhomework.domain.Genre;

public interface GenreDao {

    Genre save(Genre genre);

    boolean delete(long id);

    Genre getById(long id);

    Genre getOrCreateByName(String name);

}
