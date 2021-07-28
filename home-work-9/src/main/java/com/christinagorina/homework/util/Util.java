package com.christinagorina.homework.util;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.to.BookTo;

import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static Book createNewFromTo(BookTo bookTo, List<Author> authors, Genre genre) {
        return new Book(bookTo.getId(), bookTo.getName(), genre, null, authors);
    }

    public static BookTo createTo(Book book) {
        List<String> authorNames = book.getAuthor().stream().map(Author::getName).collect(Collectors.toList());
        List<String> comments = book.getComment().stream().map(Comment::getText).collect(Collectors.toList());
        return new BookTo(book.getId(), book.getName(), authorNames, book.getGenre().getName(), comments);
    }

    public static BookTo createToWithoutComments(Book book) {
        List<String> authorNames = book.getAuthor().stream().map(Author::getName).collect(Collectors.toList());
        return new BookTo(book.getId(), book.getName(), authorNames, book.getGenre().getName(), null);
    }
}
