package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.to.BookTo;

public interface CommentService {

    Comment createByText(String text, Book book);

    Comment findById(String id);
}
