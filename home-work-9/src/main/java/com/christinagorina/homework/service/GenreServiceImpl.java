package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.GenreDao;
import com.christinagorina.homework.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{
    private final GenreDao genreDao;

    public Genre getOrCreateByName(String name) {
        return genreDao.getOrCreateByName(name);
    }
}
