package com.christinagorina.moddbhomework;

import com.christinagorina.moddbhomework.dao.AuthorDaoJdbc;
import com.christinagorina.moddbhomework.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static com.christinagorina.moddbhomework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для AuthorDaoJdbc")
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTests {
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void updateAuthor() {
        Author existingAuthor = authorDaoJdbc.getById(1);
        existingAuthor.setName(UPDATED_AUTHOR_NAME);
        Author expectedAuthor = authorDaoJdbc.save(existingAuthor);
        assertThat(expectedAuthor.getName()).isEqualTo(UPDATED_AUTHOR_NAME);
    }

    @Test
    void createAuthor() {
        Author newAuthor = new Author(null, CREATED_AUTHOR_NAME);
        newAuthor = authorDaoJdbc.save(newAuthor);
        Author expectedAuthor = authorDaoJdbc.getById(newAuthor.getId());
        assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(newAuthor);
    }

    @Test
    void deleteAuthor() {
        boolean isDeleted = authorDaoJdbc.delete(2);
        assertThat(isDeleted).isTrue();
    }

    @Test
    void getByIdAuthor() {
        Author actualAuthor = authorDaoJdbc.getById(1);
        Author expectedAuthor = new Author(1L, AUTHOR_1_NAME);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}
