package com.christinagorina.moddbhomework;

import com.christinagorina.moddbhomework.dao.BookDaoJdbc;
import com.christinagorina.moddbhomework.domain.Author;
import com.christinagorina.moddbhomework.domain.Book;
import com.christinagorina.moddbhomework.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import java.util.ArrayList;
import java.util.List;

import static com.christinagorina.moddbhomework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для BookDaoJdbc")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTests {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    void updateBook() {
        Book expectedBook = bookDaoJdbc.getById(1);
        expectedBook.setName(UPDATED_BOOK_NAME);
        Book actualBook = bookDaoJdbc.save(expectedBook);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void createBook() {
        Author author = new Author(1L, AUTHOR_1_NAME);
        Genre genre = new Genre(1L, GENRE_1_NAME);
        Book expectedBook = new Book(null, CREATED_BOOK_NAME, author, genre);
        expectedBook = bookDaoJdbc.save(expectedBook);
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void deleteBook() {
        boolean isDeleted = bookDaoJdbc.delete(2);
        assertThat(isDeleted).isTrue();
    }

    @Test
    void getByIdBook() {
        Book actualBook = bookDaoJdbc.getById(1);
        Author author = new Author(1L, AUTHOR_1_NAME);
        Genre genre = new Genre(1L, GENRE_1_NAME);
        Book expectedBook = new Book(1L, BOOK_1_NAME, author, genre);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getAllBook() {
        List<Book> actualBookList = bookDaoJdbc.getAll();
        Author author1 = new Author(1L, AUTHOR_1_NAME);
        Genre genre1 = new Genre(1L, GENRE_1_NAME);
        Author author2 = new Author(2L, AUTHOR_2_NAME);
        Genre genre2 = new Genre(2L, GENRE_2_NAME);
        Author author3 = new Author(3L, AUTHOR_3_NAME);
        Genre genre3 = new Genre(3L, GENRE_3_NAME);
        Book book1 = new Book(1L, BOOK_1_NAME, author1, genre1);
        Book book2 = new Book(2L, BOOK_2_NAME, author2, genre2);
        Book book3 = new Book(3L, BOOK_3_NAME, author3, genre3);

        List<Book> expectedBookList = new ArrayList<>();
        expectedBookList.add(book1);
        expectedBookList.add(book2);
        expectedBookList.add(book3);

        assertThat(actualBookList).usingRecursiveComparison().isEqualTo(expectedBookList);
    }

}
