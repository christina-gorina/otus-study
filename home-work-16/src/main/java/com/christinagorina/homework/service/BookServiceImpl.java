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

    @Transactional(readOnly = true)
    public BookTo getById(long id) {
        Optional<Book> bookOptional = bookDao.findById(id);

        return bookOptional.map(Util::createTo).orElse(null);

    }

    @Transactional
    public void delete(long id) {
        bookDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookTo> getAll() {
        List<Book> books = bookDao.findAll();
        return books.stream().map(Util::createToWithoutComments).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
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
