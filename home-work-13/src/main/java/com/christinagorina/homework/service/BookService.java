package com.christinagorina.homework.service;

import com.christinagorina.homework.to.BookTo;

import java.util.List;

public interface BookService {

    BookTo update(BookTo updatedBookTo);

    BookTo create(BookTo createdBookTo);

    BookTo findById(String id);

    void deleteById(String id);

    List<BookTo> getAll();
}
