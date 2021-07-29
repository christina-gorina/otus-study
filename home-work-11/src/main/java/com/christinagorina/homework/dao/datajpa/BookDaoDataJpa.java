package com.christinagorina.homework.dao.datajpa;

import com.christinagorina.homework.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDaoDataJpa extends JpaRepository<Book, Long> {

    @Query("select b from Book b join fetch b.genre")
    List<Book> getAll();

    @Modifying
    @Query("delete from Book b where b.id =:id")
    int deleteItem(@Param("id") long id);

}

