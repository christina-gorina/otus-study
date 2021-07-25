package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.CommentDao;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentDao commentDao;

    public Comment createByText(String text, Book book) {
        return commentDao.save(new Comment(null, text, book));
    }
}
