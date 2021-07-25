package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Comment;

public interface CommentDao {

    Comment save(Comment comment);

    boolean delete(long id);

    Comment getById(long id);
}
