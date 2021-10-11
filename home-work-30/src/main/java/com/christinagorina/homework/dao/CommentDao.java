package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "comment")
public interface CommentDao extends JpaRepository<Comment, Long> {

}
