package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
