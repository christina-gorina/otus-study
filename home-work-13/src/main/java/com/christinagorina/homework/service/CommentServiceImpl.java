package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.CommentRepository;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment createByText(String text, Book book) {

        return commentRepository.save(new Comment(null, text, book.getId(), LocalDateTime.now()));
    }

    @Transactional(readOnly = true)
    @Override
    public Comment findById(String id) {

        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional.orElse(null);
    }

}
