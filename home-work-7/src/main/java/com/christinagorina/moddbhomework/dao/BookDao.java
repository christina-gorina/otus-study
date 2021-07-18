package com.christinagorina.moddbhomework.dao;

import com.christinagorina.moddbhomework.domain.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);

    boolean delete(long id);

    Book getById(long id);

    List<Book> getAll();

}
