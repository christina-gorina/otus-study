package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.BookDao;
import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.to.BookTo;
import com.christinagorina.homework.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;
    private final AuthorServiceImpl authorServiceImpl;
    private final GenreServiceImpl genreServiceImpl;

    @Override
    public BookTo update(BookTo updatedBookTo) {
        updatedBookTo = Util.removeEmptyAuthor(updatedBookTo);
        Book book = bookDao.save(getBook(updatedBookTo));
        return Util.createTo(book);
    }

    @Override
    public BookTo create(BookTo createdBookTo) {
        createdBookTo = Util.removeEmptyAuthor(createdBookTo);
        Book book = bookDao.save(getBook(createdBookTo));
        return Util.createTo(book);
    }

    @Override
    public BookTo getById(long id) {
        Optional<Book> bookOptional = bookDao.findById(id);

        return bookOptional.map(Util::createTo).orElse(null);

    }

    @Override
    public void delete(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<BookTo> getAll() {
        List<Book> books = bookDao.findAll();
        return books.stream().map(Util::createTo).collect(Collectors.toList());
    }

    public List<String> getCommentsByBook(long id){
        Book book = bookDao.getById(id);
        return book.getComment().stream().map(Comment::getText).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Book getBook(BookTo savedBookTo) {
        String genreName = savedBookTo.getGenre();
        List<Author> authors = savedBookTo.getAuthorNames().stream()
                .map(authorServiceImpl::getOrCreateByName).collect(Collectors.toList());

        Genre genre = genreServiceImpl.getOrCreateByName(genreName);
        return Util.createNewFromTo(savedBookTo, authors, genre);
    }
}
