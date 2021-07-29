package com.christinagorina.homework.dao;

import com.christinagorina.homework.dao.datajpa.GenreDaoDataJpa;
import com.christinagorina.homework.domain.Genre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;


@AllArgsConstructor
@Repository
public class GenreDaoImpl implements GenreDao {

    private final GenreDaoDataJpa genreDaoDataJpa;

    @Override
    public Genre save(Genre genre) {
        return genreDaoDataJpa.save(genre);
    }

    @Override
    public boolean delete(long id) {
        return genreDaoDataJpa.deleteItem(id) != 0;
    }

    @Override
    public Genre getById(long id) {
        return genreDaoDataJpa.getById(id);
    }

    @Override
    public Genre getOrCreateByName(String name) {
        Genre genre = genreDaoDataJpa.findByName(name);
        if (Objects.nonNull(genre)) {
            return genre;
        }
        return save(new Genre(null, name, null));
    }

}
