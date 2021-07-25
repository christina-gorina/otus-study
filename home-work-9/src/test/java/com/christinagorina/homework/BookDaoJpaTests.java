package com.christinagorina.homework;

import com.christinagorina.homework.dao.BookDaoJpa;
import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Genre;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.List;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для BookDaoJpa")
@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTests {

    @Autowired
    private BookDaoJpa bookDaoJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void updateBook() {

        val existingBook = em.find(Book.class, 1L);
        existingBook.setName(UPDATED_BOOK_NAME);
        val expectedBook = bookDaoJpa.save(existingBook);
        assertThat(expectedBook.getName()).isEqualTo(UPDATED_BOOK_NAME);
    }

    @Test
    void createBook() {

        Author author = em.find(Author.class, 1L);
        Genre genre = em.find(Genre.class, 1L);

        Book newBook = new Book(null, CREATED_BOOK_NAME, genre, null, Collections.singletonList(author));
        val newBookCreated = bookDaoJpa.save(newBook);
        val expectedBook = em.find(Book.class, newBookCreated.getId());
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(newBookCreated);
    }

    @Test
    void deleteBook() {

        val book = em.find(Book.class, 2L);
        assertThat(book).isNotNull();
        em.detach(book);
        bookDaoJpa.delete(2L);
        val deletedBook = em.find(Book.class, 2L);
        assertThat(deletedBook).isNull();
    }

    @Test
    void getByIdBook() {

        val actualBook = bookDaoJpa.getById(1);
        assertThat(actualBook).isNotNull()
                .matches(s -> s.getName().equals(BOOK_1_NAME))
                .matches(s -> s.getId() == 1L)
                .matches(s -> s.getGenre() != null && s.getGenre().getName().equals(GENRE_1_NAME))
                .matches(s -> s.getComment().size() > 0 && s.getComment().get(0).getText().equals(COMMENT1))
                .matches(s -> s.getAuthor().size() > 0 && s.getAuthor().get(0).getName().equals(AUTHOR_1_NAME));
    }

    @Test
    void getAllBook() {
        List<Book> actualBookList = bookDaoJpa.getAll();

        assertThat(actualBookList).isNotNull()
                .matches(s -> s.get(0).getId() == 1L)
                .matches(s -> s.get(0).getName().equals(BOOK_1_NAME))
                .matches(s -> s.get(1).getId() == 2L)
                .matches(s -> s.get(1).getName().equals(BOOK_2_NAME))
                .matches(s -> s.get(2).getId() == 3L)
                .matches(s -> s.get(2).getName().equals(BOOK_3_NAME));
    }
}
