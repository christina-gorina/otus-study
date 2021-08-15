package com.christinagorina.homework;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.service.AuthorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для AuthorServiceImplTest")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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
