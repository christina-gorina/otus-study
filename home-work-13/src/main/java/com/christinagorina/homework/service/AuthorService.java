package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Author;

import java.util.List;

public interface AuthorService {

    Author getOrCreateByName(String name , String bookName);

    List<String> getBooksByAuthorName(String name);

}
