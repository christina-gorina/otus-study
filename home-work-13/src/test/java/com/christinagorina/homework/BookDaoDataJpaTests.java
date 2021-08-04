package com.christinagorina.homework;

import com.christinagorina.homework.dao.BookRepository;
import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Genre;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для BookDaoDataJpa")
@DataMongoTest
class BookDaoDataJpaTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    void updateBook() {
        val existingBook = mongoTemplate.findById("4", Book.class);
        assertThat(existingBook).isNotNull();
        existingBook.setName(UPDATED_BOOK_NAME);
        val expectedBook = bookRepository.save(existingBook);
        assertThat(expectedBook.getName()).isEqualTo(UPDATED_BOOK_NAME);
    }

    @Test
    void createBook() {
        Author author = mongoTemplate.findById("1", Author.class);
        Genre genre = mongoTemplate.findById("1", Genre.class);
        assertThat(author).isNotNull();
        assertThat(genre).isNotNull();
        Book newBook = new Book(null, CREATED_BOOK_NAME, genre.getName(), BOOK1_YEAR,BOOK1_LANGUAGE,
                Collections.singletonList(author), null);

        val newBookCreated = bookRepository.save(newBook);
        val expectedBook = mongoTemplate.findById(newBookCreated.getId(), Book.class);
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(newBookCreated);
    }

    @Test
    void deleteBook() {
        val book = mongoTemplate.findById("2", Book.class);
        assertThat(book).isNotNull();
        bookRepository.delete(book);
        val deletedBook = mongoTemplate.findById("2", Book.class);
        assertThat(deletedBook).isNull();
    }

    @Test
    void getByIdBook() {
        Optional<Book> actualBook = bookRepository.findById("1");

        assertThat(actualBook.get()).isNotNull()
                .matches(s -> s.getName().equals(BOOK_1_NAME))
                .matches(s -> s.getId().equals("1"))
                .matches(s -> s.getGenre() != null && s.getGenre().equals(GENRE_1_NAME))
                .matches(s -> s.getLastComments().size() > 0 && s.getLastComments().get(0).equals(COMMENT1))
                .matches(s -> s.getAuthors().size() > 0 && s.getAuthors().get(0).getName().equals(AUTHOR_1_NAME));
    }

    @Test
    void getAllBook() {
        List<Book> actualBookList = bookRepository.findAll();

        assertThat(actualBookList).isNotNull()
                .matches(s -> s.get(0).getId().equals("1"))
                .matches(s -> s.get(0).getName().equals(BOOK_1_NAME))
                .matches(s -> s.get(1).getId().equals("2"))
                .matches(s -> s.get(1).getName().equals(BOOK_2_NAME))
                .matches(s -> s.get(2).getId().equals("3"))
                .matches(s -> s.get(2).getName().equals(BOOK_3_NAME));
    }

}
