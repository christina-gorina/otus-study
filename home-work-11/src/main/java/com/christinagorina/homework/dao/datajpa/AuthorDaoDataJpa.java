package com.christinagorina.homework.dao.datajpa;

import com.christinagorina.homework.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorDaoDataJpa extends JpaRepository<Author, Long> {

    Author findByName(String name);

    @Modifying
    @Query("delete from Author b where b.id =:id")
    int deleteItem(@Param("id") long id);
}

