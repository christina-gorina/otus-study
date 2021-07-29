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
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;
    private final AuthorServiceImpl authorServiceImpl;
    private final GenreServiceImpl genreServiceImpl;
    private final CommentServiceImpl commentServiceImpl;

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

    public List<Comment> saveComment(List<String> comments, Book book) {
        return comments.stream()
                .map(text -> commentServiceImpl.createByText(text, book)).collect(Collectors.toList());
    }

    public BookTo getById(long id) {
        Book book = bookDao.getById(id);

        if (Objects.nonNull(book)) {
            return Util.createTo(book);
        } else {
            return null;
        }

    }

    @Transactional
    public boolean delete(long id) {
        return bookDao.delete(id);
    }

    public List<BookTo> getAll() {
        List<Book> books = bookDao.getAll();
        return books.stream().map(Util::createToWithoutComments).collect(Collectors.toList());
    }

    public List<String> getCommentsByBook(long id){
        Book book = bookDao.getById(id);
        return book.getComment().stream().map(Comment::getText).collect(Collectors.toList());
    }

    private Book getBook(BookTo savedBookTo) {
        String genreName = savedBookTo.getGenre();
        List<Author> authors = savedBookTo.getAuthorNames().stream()
                .map(authorServiceImpl::getOrCreateByName).collect(Collectors.toList());

        Genre genre = genreServiceImpl.getOrCreateByName(genreName);
        return Util.createNewFromTo(savedBookTo, authors, genre);
    }
}
