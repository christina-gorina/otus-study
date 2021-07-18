package com.christinagorina.moddbhomework.service;

import com.christinagorina.moddbhomework.dao.GenreDao;
import com.christinagorina.moddbhomework.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GenreService {
    private final GenreDao genreDao;

    public Genre getOrCreateByName(String name) {
        return genreDao.getOrCreateByName(name);
    }
}
