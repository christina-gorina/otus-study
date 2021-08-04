package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.BookRepository;
import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.to.BookTo;
import com.christinagorina.homework.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.christinagorina.homework.util.Util.COUNT_OF_LAST_COMMENTS;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorServiceImpl authorServiceImpl;
    private final GenreServiceImpl genreServiceImpl;
    private final CommentServiceImpl commentServiceImpl;

    @Transactional
    public BookTo update(BookTo updatedBookTo) {
        Book book = bookRepository.save(getBook(updatedBookTo));

        saveComment(updatedBookTo.getLastComments(), book);
        return Util.createTo(book);
    }

    @Transactional
    public BookTo create(BookTo createdBookTo) {
        Book book = bookRepository.save(getBook(createdBookTo));
        saveComment(createdBookTo.getLastComments(), book);
        return Util.createTo(book);
    }

    @Transactional(readOnly = true)
    public BookTo findById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isEmpty()){
            return null;
        }

        return bookOptional.map(Util::createTo).orElse(null);
    }

    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookTo> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(Util::createTo).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> getLastCommentsByBook(String id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isEmpty()){
            return Collections.emptyList();
        }

        return bookOptional.get().getLastComments();
    }

    @Transactional
    public List<Comment> saveComment(List<String> comments, Book book) {
        return comments.stream()
                .map(text -> commentServiceImpl.createByText(text, book))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Book getBook(BookTo savedBookTo) {
        if(Objects.isNull(savedBookTo)){
            return null;
        }

        String bookId = savedBookTo.getId();
        List<String> existingLastComments = new ArrayList<>();

        if(Objects.nonNull(bookId)){
            existingLastComments = getLastCommentsByBook(bookId);
        }

        List<String> newLastComments = newLastComment(existingLastComments, savedBookTo.getLastComments());



        String genreName = savedBookTo.getGenre();
        List<Author> authors = savedBookTo.getAuthorNames().stream()
                .map(author-> authorServiceImpl.getOrCreateByName(author, savedBookTo.getName())
                ).collect(Collectors.toList());

        Genre genre = genreServiceImpl.getOrCreateByName(genreName, savedBookTo.getName(), savedBookTo.getYear());
        genre.setBooks(null);
        authors.stream().forEach(author -> author.setBooksNames(Collections.emptyList()));

        return Util.createNewFromTo(savedBookTo, authors, genre, newLastComments);
    }

    private List<String> newLastComment(List<String> existingLastComments, List<String> newComments) {
        List<String> newLastComments = new ArrayList<>();
        int newCommentsListSize =  newComments.size();
        int count = Math.min(newCommentsListSize, COUNT_OF_LAST_COMMENTS);

        for (int i = 0; i < count; i++){
            existingLastComments.add(newComments.get(i));
        }

        Collections.reverse(existingLastComments);
        if(existingLastComments.size() >= COUNT_OF_LAST_COMMENTS){
            for (int j = 0; j < COUNT_OF_LAST_COMMENTS; j++){
                newLastComments.add(existingLastComments.get(j));
            }
            return newLastComments;
        }else{
            return  existingLastComments;
        }




    }
}
