package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "book")
public interface BookDao extends JpaRepository<Book, Long> {

}
