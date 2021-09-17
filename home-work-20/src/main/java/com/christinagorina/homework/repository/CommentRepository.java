package com.christinagorina.homework.repository;

import com.christinagorina.homework.domain.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

}
