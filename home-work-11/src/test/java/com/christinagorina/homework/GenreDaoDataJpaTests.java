package com.christinagorina.homework;

import com.christinagorina.homework.dao.GenreDaoImpl;
import com.christinagorina.homework.domain.Genre;
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

@DisplayName("Тест для GenreDaoDataJpa")
@DataJpaTest
@Import(GenreDaoImpl.class)
public class GenreDaoDataJpaTests {

    @Autowired
    private GenreDaoImpl genreDaoImpl;

    @Autowired
    private TestEntityManager em;

    @Test
    void getByIdGenre() {
        Genre actualGenre = genreDaoImpl.getById(1L);
        assertThat(actualGenre).isNotNull()
                .matches(s -> s.getName().equals(GENRE_1_NAME))
                .matches(s -> s.getId() == 1L);
    }

    @Test
    void updateGenre() {
        val existingGenre = em.find(Genre.class, 1L);
        existingGenre.setName(UPDATED_GENRE_NAME);
        val expectedGenre = genreDaoImpl.save(existingGenre);
        assertThat(expectedGenre.getName()).isEqualTo(UPDATED_GENRE_NAME);
    }

    @Test
    void createGenre() {
        val newGenre = new Genre(null, CREATED_GENRE_NAME, null);
        val newGenreCreated = genreDaoImpl.save(newGenre);
        val expectedGenre = em.find(Genre.class, newGenreCreated.getId());
        assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(newGenreCreated);
    }

    @Test
    void deleteById() {
        val genre = em.find(Genre.class, 2L);
        assertThat(genre).isNotNull();
        em.detach(genre);
        genreDaoImpl.delete(2L);
        val deletedGenre = em.find(Genre.class, 2L);
        assertThat(deletedGenre).isNull();
    }

}
