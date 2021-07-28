package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Objects;

@Repository
public class CommentDaoJpa implements CommentDao{

    @PersistenceContext
    private final EntityManager em;

    public CommentDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment) {
        if (Objects.isNull(comment.getId())) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Comment getById(long id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c join fetch c.book where c.id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public boolean delete(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

}
