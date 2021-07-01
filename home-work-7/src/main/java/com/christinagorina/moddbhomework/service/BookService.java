package com.christinagorina.moddbhomework.service;

import com.christinagorina.moddbhomework.dao.BookDao;
import com.christinagorina.moddbhomework.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book update(Book book) {
        return bookDao.save(book);
    }

    public Book create(Book book) {
        return bookDao.save(book);
    }

    public boolean delete(long id) {
        return bookDao.delete(id);
    }

    public Book getById(long id) {
        return bookDao.getById(id);
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
