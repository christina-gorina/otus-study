package com.christinagorina.homework;

import com.christinagorina.homework.dao.AuthorRepository;
import com.christinagorina.homework.domain.Author;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для AuthorDaoDataJpa")
@DataMongoTest
public class AuthorDaoDataJpaTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void updateAuthor() {
        val existingAuthor = mongoTemplate.findById("3", Author.class);
        assertThat(existingAuthor).isNotNull();
        existingAuthor.setName(UPDATED_AUTHOR_NAME);
        val expectedAuthor = authorRepository.save(existingAuthor);
        assertThat(expectedAuthor.getName()).isEqualTo(UPDATED_AUTHOR_NAME);
    }

    @Test
    void createAuthor() {
        val newAuthor = new Author(null, CREATED_AUTHOR_NAME, null);
        val newAuthorCreated = authorRepository.save(newAuthor);
        val expectedAuthor = mongoTemplate.findById(newAuthorCreated.getId(), Author.class);
        assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(newAuthorCreated);
    }

    @Test
    void deleteById() {
        val author = mongoTemplate.findById("2", Author.class);
        assertThat(author).isNotNull();
        authorRepository.delete(author);
        val deletedAuthor = mongoTemplate.findById("2", Author.class);
        assertThat(deletedAuthor).isNull();
    }

    @Test
    void getByIdAuthor() {
        Optional<Author> actualAuthor = authorRepository.findById("1");
        assertThat(actualAuthor.get()).isNotNull()
                .matches(s -> s.getName().equals(AUTHOR_1_NAME))
                .matches(s -> s.getBooksNames().get(0).equals(BOOK_1_NAME))
                .matches(s -> s.getId().equals("1"));
    }

    @Test
    void findByName() {
        Author actualAuthor = authorRepository.findByName(AUTHOR_1_NAME);
        assertThat(actualAuthor).isNotNull()
                .matches(s -> s.getName().equals(AUTHOR_1_NAME))
                .matches(s -> s.getBooksNames().get(0).equals(BOOK_1_NAME))
                .matches(s -> s.getId().equals("1"));
    }

}
