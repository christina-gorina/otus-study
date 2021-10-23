package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Long> {

    Author findByName(String name);

}
