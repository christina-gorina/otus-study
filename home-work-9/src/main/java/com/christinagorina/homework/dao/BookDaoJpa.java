package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
        EntityGraph<?> entityGraph = em.getEntityGraph("book-comment-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genre where b.id = :id", Book.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public boolean delete(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-comment-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genre", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}