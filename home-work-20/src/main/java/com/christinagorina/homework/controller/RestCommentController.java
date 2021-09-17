package com.christinagorina.homework.controller;

import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class RestCommentController {

    private final CommentRepository commentRepository;

    @GetMapping("/api/comments")
    public Flux<Comment> findAllComments() {
        return commentRepository.findAll();
    }

}
