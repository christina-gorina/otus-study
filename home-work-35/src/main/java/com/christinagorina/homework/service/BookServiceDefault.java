package com.christinagorina.homework.service;

import com.christinagorina.homework.to.BookTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookServiceDefault {

    public List<BookTo> getAllFailure() {
        BookTo bookTo1 = new BookTo(1L, "Love story", Collections.singletonList("N/A"), "N/A");
        BookTo bookTo2 = new BookTo(2L, "Call of the night", Collections.singletonList("N/A"), "N/A");
        BookTo bookTo3 = new BookTo(3L, "Close your eyes", Collections.singletonList("N/A"), "N/A");
        List<BookTo> bookTos = new ArrayList<>();
        bookTos.add(bookTo1);
        bookTos.add(bookTo2);
        bookTos.add(bookTo3);
        return bookTos;
    }

    public BookTo getByIdFailure(long id) {
        return new BookTo(id, "N/A", Collections.singletonList("N/A"), "N/A");
    }
}
