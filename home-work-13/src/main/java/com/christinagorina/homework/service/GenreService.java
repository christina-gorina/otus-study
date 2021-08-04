package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre getOrCreateByName(String name, String bookName, String bookYear);

    List<Book> getBooksByGenreName(String name);
}
