package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;

public interface CommentService {
    Comment createByText(String text, Book book);
}
