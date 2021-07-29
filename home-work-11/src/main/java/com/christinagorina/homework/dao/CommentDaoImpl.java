package com.christinagorina.homework.dao;

import com.christinagorina.homework.dao.datajpa.CommentDaoDataJpa;
import com.christinagorina.homework.domain.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class CommentDaoImpl implements CommentDao {

    private final CommentDaoDataJpa commentDaoDataJpa;

    @Override
    public Comment save(Comment comment) {
        return commentDaoDataJpa.save(comment);
    }

    @Override
    public boolean delete(long id) {
        return commentDaoDataJpa.deleteItem(id) != 0;
    }

    @Override
    public Comment getById(long id) {
        return commentDaoDataJpa.getById(id);
    }

}
