package com.christinagorina.moddbhomework.service;

import com.christinagorina.moddbhomework.dao.BookDao;
import com.christinagorina.moddbhomework.domain.Author;
import com.christinagorina.moddbhomework.domain.Book;
import com.christinagorina.moddbhomework.domain.Genre;
import com.christinagorina.moddbhomework.to.BookTo;
import com.christinagorina.moddbhomework.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookTo update(BookTo updatedBookTo) {
        Book book = bookDao.save(getBook(updatedBookTo));
        return Util.createTo(book);
    }

    public BookTo create(BookTo createdBookTo) {
        Book book = bookDao.save(getBook(createdBookTo));
        return Util.createTo(book);
    }

    private Book getBook(BookTo createdBookTo){
        String authorName = createdBookTo.getAuthor();
        String genreName = createdBookTo.getGenre();
        Author author = authorService.getOrCreateByName(authorName);
        Genre genre = genreService.getOrCreateByName(genreName);
        return Util.createNewFromTo(createdBookTo, author, genre);
    }

    public boolean delete(long id) {
        return bookDao.delete(id);
    }

    public BookTo getById(long id) {
        Book book = bookDao.getById(id);
        return Util.createTo(book);
    }

    public List<BookTo> getAll() {
        List<Book> books = bookDao.getAll();
        return books.stream().map(Util::createTo).collect(Collectors.toList());
    }
}
