package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.GenreDao;
import com.christinagorina.homework.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{
    private final GenreDao genreDao;

    public Genre getOrCreateByName(String name) {
        Genre genre = genreDao.findByName(name);
        if (Objects.nonNull(genre)) {
            return genre;
        }
        return genreDao.save(new Genre(null, name, null));
    }

}
