package com.christinagorina.homework;

import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.service.GenreServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.christinagorina.homework.TestData.GENRE_1_NAME;
import static com.christinagorina.homework.TestData.NEW_GENRE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для GenreServiceImplTest")
@SpringBootTest
@Transactional
public class GenreServiceImplTest {

    @Autowired
    private GenreServiceImpl genreServiceImpl;

    @Test
    void getOrCreateByExistingName() {
        Genre actualGenre = genreServiceImpl.getOrCreateByName(GENRE_1_NAME);
        assertThat(actualGenre).isNotNull()
                .matches(s -> s.getName().equals(GENRE_1_NAME))
                .matches(s -> s.getId() == 1L);
    }

    @Test
    void getOrCreateByNewName() {
        Genre actualGenre = genreServiceImpl.getOrCreateByName(NEW_GENRE_NAME);
        assertThat(actualGenre).isNotNull()
                .matches(s -> s.getName().equals(NEW_GENRE_NAME));
    }
}
