package com.christinagorina.homework.util;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.to.BookTo;

import java.util.*;
import java.util.stream.Collectors;

public class Util {

    public static Book createNewFromTo(BookTo bookTo, List<Author> authors, Genre genre) {
        return new Book(bookTo.getId(), bookTo.getName(), genre, null, authors);
    }

    public static BookTo createTo(Book book) {
        if(Objects.nonNull(book)){
            List<String> authorNames = book.getAuthor().stream().map(Author::getName).sorted().collect(Collectors.toList());
            return new BookTo(book.getId(), book.getName(), authorNames, book.getGenre().getName());
        }else{
            return new BookTo();
        }
    }

    public static BookTo removeEmptyAuthor(BookTo createdBookTo) {
        if(Objects.nonNull(createdBookTo)){
            List<String> authorNamesList = Optional.of(createdBookTo).map(BookTo::getAuthorNames).
                    orElse(Collections.emptyList()).stream().filter(n->!n.isEmpty()).
                    collect(Collectors.toList());

            createdBookTo.setAuthorNames(authorNamesList);
            return createdBookTo;
        }else{
            return new BookTo();
        }
    }

}
