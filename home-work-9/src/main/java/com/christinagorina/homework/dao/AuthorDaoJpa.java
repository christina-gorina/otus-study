package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Objects;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    public AuthorDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author save(Author author) {
        if (Objects.isNull(author.getId())) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }


    @Override
    public boolean delete(long id) {
        Query query = em.createQuery("delete from Author a where a.id = :id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Author getById(long id) {

        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id = :id", Author.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public Author getOrCreateByName(String name) {

        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        Author author = query.setParameter("name", name).getResultList().stream().findFirst().orElse(null);

        if (Objects.nonNull(author)) {
            return author;
        }

        return save(new Author(null, name, null));
    }

}
