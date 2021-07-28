package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Objects;

@Repository
public class GenreDaoJpa implements GenreDao {
    @PersistenceContext
    private final EntityManager em;

    public GenreDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre save(Genre genre) {
        if (Objects.isNull(genre.getId())) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Genre getById(long id) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.id = :id", Genre.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public boolean delete(long id) {
        Query query = em.createQuery("delete from Genre g where g.id = :id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Genre getOrCreateByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :name", Genre.class);
        Genre genre = query.setParameter("name", name).getResultList().stream().findFirst().orElse(null);
        if (Objects.nonNull(genre)) {
            return genre;
        }
        return save(new Genre(null, name, null));
    }
}
