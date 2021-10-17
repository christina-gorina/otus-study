package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Long> {

}
