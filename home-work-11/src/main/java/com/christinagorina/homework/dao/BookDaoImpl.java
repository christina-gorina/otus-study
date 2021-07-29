package com.christinagorina.homework.dao;

import com.christinagorina.homework.dao.datajpa.BookDaoDataJpa;
import com.christinagorina.homework.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

    private final BookDaoDataJpa bookDaoDataJpa;

    @Override
    public Book save(Book book) {
        return bookDaoDataJpa.save(book);
    }

    @Override
    public boolean delete(long id) {
        return bookDaoDataJpa.deleteItem(id) != 0;
    }

    @Override
    public Book getById(long id) {
        return bookDaoDataJpa.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDaoDataJpa.getAll();
    }

}