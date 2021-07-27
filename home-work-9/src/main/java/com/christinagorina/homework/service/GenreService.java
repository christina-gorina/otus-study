package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Genre;

public interface GenreService {
    Genre getOrCreateByName(String name);
}
