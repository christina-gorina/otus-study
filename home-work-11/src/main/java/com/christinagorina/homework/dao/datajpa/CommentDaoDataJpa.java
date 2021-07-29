package com.christinagorina.homework.dao.datajpa;

import com.christinagorina.homework.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentDaoDataJpa extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("delete from Comment b where b.id =:id")
    int deleteItem(@Param("id") long id);
}

