package com.christinagorina.homework;

import com.christinagorina.homework.dao.AuthorDao;
import com.christinagorina.homework.domain.Author;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для AuthorDao")
@DataJpaTest
@ContextConfiguration
public class AuthorDaoDataJpaTests {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void getByIdAuthor() {
        val actualAuthor = authorDao.findById(1L);
        assertThat(actualAuthor.get()).isNotNull()
                .matches(s -> s.getName().equals(AUTHOR_1_NAME))
                .matches(s -> s.getId() == 1L);
    }

    @Test
    void updateAuthor() {
        val existingComment = em.find(Author.class, 1L);
        existingComment.setName(UPDATED_AUTHOR_NAME);
        val expectedComment = authorDao.save(existingComment);
        assertThat(expectedComment.getName()).isEqualTo(UPDATED_AUTHOR_NAME);
    }

    @Test
    void createAuthor() {
        Author newAuthor = new Author(null, CREATED_AUTHOR_NAME, null);
        val newAuthorCreated = authorDao.save(newAuthor);
        val expectedAuthor = em.find(Author.class, newAuthorCreated.getId());
        assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(newAuthorCreated);
    }

    @Test
    void deleteAuthor() {
        val author = em.find(Author.class, 2L);
        assertThat(author).isNotNull();
        authorDao.deleteById(2L);
        val deletedAuthor = em.find(Author.class, 2L);
        assertThat(deletedAuthor).isNull();
    }

}
