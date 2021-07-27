package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    public BookDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (Objects.isNull(book.getId())) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Book getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id)).orElse(null);
    }

    @Override
    public void delete(long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b join fetch b.genre", Book.class).getResultList();
    }
}