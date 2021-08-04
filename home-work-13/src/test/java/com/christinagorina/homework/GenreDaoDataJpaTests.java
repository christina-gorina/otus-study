package com.christinagorina.homework;

import com.christinagorina.homework.dao.GenreRepository;
import com.christinagorina.homework.domain.Genre;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для GenreDaoDataJpa")
@DataMongoTest
public class GenreDaoDataJpaTests {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void updateGenre() {
        val existingGenre = mongoTemplate.findById("3", Genre.class);
        assertThat(existingGenre).isNotNull();
        existingGenre.setName(UPDATED_GENRE_NAME);
        val expectedGenre = genreRepository.save(existingGenre);
        assertThat(expectedGenre.getName()).isEqualTo(UPDATED_GENRE_NAME);
    }

    @Test
    void createGenre() {
        val newGenre = new Genre(null, CREATED_GENRE_NAME, null);
        val newGenreCreated = genreRepository.save(newGenre);
        val expectedGenre = mongoTemplate.findById(newGenreCreated.getId(), Genre.class);
        assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(newGenreCreated);
    }

    @Test
    void deleteById() {
        val genre = mongoTemplate.findById("2", Genre.class);
        assertThat(genre).isNotNull();
        genreRepository.delete(genre);
        val deletedGenre = mongoTemplate.findById("2", Genre.class);
        assertThat(deletedGenre).isNull();
    }

    @Test
    void getByIdGenre() {
        Optional<Genre> actualGenre = genreRepository.findById("1");
        assertThat(actualGenre.get()).isNotNull()
                .matches(s -> s.getName().equals(GENRE_1_NAME))
                .matches(s -> s.getBooks().get(0).getName().equals(BOOK_1_NAME))
                .matches(s -> s.getBooks().get(0).getYear().equals(BOOK1_YEAR))
                .matches(s -> s.getId().equals("1"));
    }

    @Test
    void findByName() {
        Genre actualGenre = genreRepository.findByName(GENRE_1_NAME);
        assertThat(actualGenre).isNotNull()
                .matches(s -> s.getName().equals(GENRE_1_NAME))
                .matches(s -> s.getBooks().get(0).getName().equals(BOOK_1_NAME))
                .matches(s -> s.getBooks().get(0).getYear().equals(BOOK1_YEAR))
                .matches(s -> s.getId().equals("1"));
    }

}
