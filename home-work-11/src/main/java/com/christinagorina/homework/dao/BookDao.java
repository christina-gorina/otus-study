package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);

    boolean delete(long id);

    Book getById(long id);

    List<Book> getAll();

}
