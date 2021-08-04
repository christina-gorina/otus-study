package com.christinagorina.homework.util;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.to.BookTo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    public static final int COUNT_OF_LAST_COMMENTS = 3;

    public static Book createNewFromTo(BookTo bookTo, List<Author> authors, Genre genre, List<String> lastComments) {
        return new Book(bookTo.getId(), bookTo.getName(), genre.getName(), bookTo.getYear(),
                bookTo.getLanguage(), authors, lastComments);
    }

    public static BookTo createTo(Book book) {
        if(Objects.nonNull(book)){
            List<String> authorNames = book.getAuthors().stream().map(Author::getName).collect(Collectors.toList());
            return new BookTo(book.getId(), book.getName(), book.getGenre(), book.getYear(),
                    book.getLanguage(), authorNames, book.getLastComments());
        }else{
            return new BookTo();
        }

    }
}
