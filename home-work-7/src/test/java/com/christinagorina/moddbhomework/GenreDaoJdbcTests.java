package com.christinagorina.moddbhomework;

import com.christinagorina.moddbhomework.dao.GenreDaoJdbc;
import com.christinagorina.moddbhomework.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для GenreDaoJdbc")
@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTests {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    private static final String GENRE_1_NAME = "Mystic";
    private static final String UPDATED_GENRE_NAME = "Anime";
    private static final String CREATED_GENRE_NAME = "Drama";

    @Test
    void updateGenre() {
        Genre existingGenre = genreDaoJdbc.getById(1);
        existingGenre.setName(UPDATED_GENRE_NAME);
        Genre expectedGenre = genreDaoJdbc.save(existingGenre);
        assertThat(expectedGenre.getName()).isEqualTo(UPDATED_GENRE_NAME);
    }

    @Test
    void createGenre() {
        Genre newGenre = new Genre(null, CREATED_GENRE_NAME);
        newGenre = genreDaoJdbc.save(newGenre);
        Genre expectedGenre = genreDaoJdbc.getById(newGenre.getId());
        assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(newGenre);
    }

    @Test
    void deleteGenre() {
        boolean isDeleted = genreDaoJdbc.delete(2);
        assertThat(isDeleted).isTrue();
    }

    @Test
    void getByIdGenre() {
        Genre actualGenre = genreDaoJdbc.getById(1);
        Genre expectedGenre = new Genre(1L, GENRE_1_NAME);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}
