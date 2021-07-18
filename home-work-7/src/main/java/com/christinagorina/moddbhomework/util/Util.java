package com.christinagorina.moddbhomework.util;

import com.christinagorina.moddbhomework.domain.Author;
import com.christinagorina.moddbhomework.domain.Book;
import com.christinagorina.moddbhomework.domain.Genre;
import com.christinagorina.moddbhomework.to.BookTo;

public class Util {
    public static Book createNewFromTo(BookTo bookTo, Author author, Genre genre) {
        return new Book(bookTo.getId(), bookTo.getName(), author, genre);
    }

    public static BookTo createTo(Book book) {
        return new BookTo(book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName());
    }
}
