package com.christinagorina.homework;

import com.christinagorina.homework.dao.AuthorDaoImpl;
import com.christinagorina.homework.domain.Author;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для AuthorDaoDataJpa")
@DataJpaTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoDataJpaTests {
    @Autowired
    private AuthorDaoImpl authorDaoImpl;

    @Autowired
    private TestEntityManager em;

    @Test
    void getByIdAuthor() {
        val actualAuthor = authorDaoImpl.getById(1);
        assertThat(actualAuthor).isNotNull()
                .matches(s -> s.getName().equals(AUTHOR_1_NAME))
                .matches(s -> s.getId() == 1L);
    }

    @Test
    void updateAuthor() {
        val existingComment = em.find(Author.class, 1L);
        existingComment.setName(UPDATED_AUTHOR_NAME);
        val expectedComment = authorDaoImpl.save(existingComment);
        assertThat(expectedComment.getName()).isEqualTo(UPDATED_AUTHOR_NAME);
    }

    @Test
    void createAuthor() {

        Author newAuthor = new Author(null, CREATED_AUTHOR_NAME, null);
        val newAuthorCreated = authorDaoImpl.save(newAuthor);
        val expectedAuthor = em.find(Author.class, newAuthorCreated.getId());
        assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(newAuthorCreated);
    }

    @Test
    void deleteAuthor() {

        boolean isDeleted = authorDaoImpl.delete(2);
        assertThat(isDeleted).isTrue();
    }

}
