package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.repository.AuthorRepository;
import com.christinagorina.homework.repository.BookRepository;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.repository.CommentRepository;
import com.christinagorina.homework.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @Override
    public Mono<Book> save(Book createdBook) {
        return bookRepository.save(createdBook).flatMap(this::saveGenre).
                flatMap(this::saveAuthor).flatMap(this::saveComments);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Mono<List<Book>> findAll() {
        return bookRepository.findAll().collect(Collectors.toList());
    }

    private Mono<Book> saveComments(Book bookSaved) {
        List<Comment> comments = bookSaved.getComments().stream().map(text -> {
            Comment comment = new Comment();
            comment.setBookId(bookSaved.getId());
            comment.setText(text);
            return comment;
        }).collect(Collectors.toList());

        return commentRepository.saveAll(comments).next().map(c -> bookSaved);
    }

    private Mono<Book> saveAuthor(Book bookSaved) {
        return bookSaved.getAuthors().stream().map(author -> authorRepository.findByName(author).map(a -> {
            a.getBooksNames().add(bookSaved.getName());
            return a;
        }).defaultIfEmpty(new Author(null, author, Collections.singletonList(bookSaved.getName()))).
                flatMap(authorRepository::save).map(n -> bookSaved)).findAny().orElse(Mono.empty());

    }

    private Mono<Book> saveGenre(Book bookSaved) {
        return genreRepository.findByName(bookSaved.getGenre()).
                map(g -> {
                    g.getBooks().add(bookSaved);
                    return g;
                }).
                defaultIfEmpty(new Genre(null, bookSaved.getGenre(), Collections.singletonList(bookSaved))).
                flatMap(genreRepository::save).map(n -> bookSaved);
    }

}
