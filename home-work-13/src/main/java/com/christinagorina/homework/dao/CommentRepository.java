package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
