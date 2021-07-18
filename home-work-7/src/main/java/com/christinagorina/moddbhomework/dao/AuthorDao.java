package com.christinagorina.moddbhomework.dao;

import com.christinagorina.moddbhomework.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author save(Author author);

    boolean delete(long id);

    Author getById(long id);

    Author getOrCreateByName(String name);

}
