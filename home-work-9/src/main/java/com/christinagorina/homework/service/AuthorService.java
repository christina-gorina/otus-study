package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Author;

public interface AuthorService {

    Author getOrCreateByName(String name);


}
