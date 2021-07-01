package com.christinagorina.moddbhomework.dao;

import com.christinagorina.moddbhomework.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre save(Genre genre);

    boolean delete(long id);

    Genre getById(long id);

}
