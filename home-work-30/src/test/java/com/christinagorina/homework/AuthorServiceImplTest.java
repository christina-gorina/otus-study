package com.christinagorina.homework;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.service.AuthorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.christinagorina.homework.TestData.AUTHOR_1_NAME;
import static com.christinagorina.homework.TestData.NEW_AUTHOR_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для AuthorServiceImplTest")
@Transactional
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    @Test
    void getOrCreateByExistingName() {
        Author actualAuthor = authorServiceImpl.getOrCreateByName(AUTHOR_1_NAME);
        assertThat(actualAuthor).isNotNull()
                .matches(s -> s.getName().equals(AUTHOR_1_NAME))
                .matches(s -> s.getId() == 1L);
    }

    @Test
    void getOrCreateByNewName() {
        Author actualAuthor = authorServiceImpl.getOrCreateByName(NEW_AUTHOR_NAME);
        assertThat(actualAuthor).isNotNull()
                .matches(s -> s.getName().equals(NEW_AUTHOR_NAME));
    }
}
