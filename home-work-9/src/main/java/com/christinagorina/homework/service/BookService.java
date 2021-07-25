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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @Transactional
    public BookTo update(BookTo updatedBookTo) {
        Book book = bookDao.save(getBook(updatedBookTo));
        List<Comment> comments = saveComment(updatedBookTo.getComments(), book);
        book.setComment(comments);
        return Util.createTo(book);
    }

    @Transactional
    public BookTo create(BookTo createdBookTo) {
        Book book = bookDao.save(getBook(createdBookTo));
        List<Comment> comments = saveComment(createdBookTo.getComments(), book);
        book.setComment(comments);
        return Util.createTo(book);
    }

    private Book getBook(BookTo savedBookTo) {
        String genreName = savedBookTo.getGenre();
        List<Author> authors = savedBookTo.getAuthorNames().stream()
                .map(authorService::getOrCreateByName).collect(Collectors.toList());

        Genre genre = genreService.getOrCreateByName(genreName);
        return Util.createNewFromTo(savedBookTo, authors, genre);
    }

    public List<Comment> saveComment(List<String> comments, Book book) {
        return comments.stream()
                .map(text -> commentService.createByText(text, book)).collect(Collectors.toList());
    }

    public BookTo getById(long id) {
        Book book = bookDao.getById(id);
        return Util.createTo(book);
    }

    public boolean delete(long id) {
        return bookDao.delete(id);
    }

    public List<BookTo> getAll() {
        List<Book> books = bookDao.getAll();
        return books.stream().map(Util::createTo).collect(Collectors.toList());
    }
}
