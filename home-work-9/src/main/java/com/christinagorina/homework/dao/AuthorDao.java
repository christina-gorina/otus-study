package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Author;

public interface AuthorDao {

    Author save(Author author);

    boolean delete(long id);

    Author getById(long id);

    Author getOrCreateByName(String name);

}
