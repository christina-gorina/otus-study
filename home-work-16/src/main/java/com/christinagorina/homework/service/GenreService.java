package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre getOrCreateByName(String name);

    List<Genre> findAll();

}
