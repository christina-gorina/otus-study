package com.christinagorina.homework;

import com.christinagorina.homework.service.BookService;
import com.christinagorina.homework.to.BookTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для BookServiceTest")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Transactional
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Test
    void updateBook() {
        BookTo expectedBookTo = bookService.getById(1);
        expectedBookTo.setName(NEW_BOOK_NAME);
        expectedBookTo.setAuthorNames(Collections.singletonList(NEW_AUTHOR_NAME));
        expectedBookTo.setGenre(NEW_GENRE_NAME);

        BookTo actualBookTo = bookService.update(expectedBookTo);
        assertThat(actualBookTo).usingRecursiveComparison().isEqualTo(expectedBookTo);
    }

    @Test
    void createBook() {
        BookTo actualBookTo = new BookTo(null, NEW_BOOK_NAME, Collections.singletonList(NEW_AUTHOR_NAME), NEW_GENRE_NAME, Collections.singletonList(NEW_COMMENT));
        actualBookTo = bookService.create(actualBookTo);
        BookTo expectedBookTo = bookService.getById(actualBookTo.getId());
        assertThat(actualBookTo).usingRecursiveComparison().isEqualTo(expectedBookTo);
    }

    @Test
    void deleteBook() {
        boolean isDeleted = bookService.delete(2);
        assertThat(isDeleted).isTrue();
    }

    @Test
    void getByIdBook() {
        BookTo actualBookTo = bookService.getById(1);
        BookTo expectedBookTo = new BookTo(1L, BOOK_1_NAME, Collections.singletonList(AUTHOR_1_NAME), GENRE_1_NAME, Arrays.asList(COMMENT1, COMMENT2, COMMENT3, COMMENT4, COMMENT5));
        assertThat(actualBookTo).usingRecursiveComparison().isEqualTo(expectedBookTo);
    }

    @Test
    void getAllBook() {
        List<BookTo> actualBookList = bookService.getAll();

        assertThat(actualBookList).isNotNull()
                .matches(s -> s.get(0).getId() == 1L)
                .matches(s -> s.get(0).getName().equals(BOOK_1_NAME))
                .matches(s -> s.get(1).getId() == 2L)
                .matches(s -> s.get(1).getName().equals(BOOK_2_NAME))
                .matches(s -> s.get(2).getId() == 3L)
                .matches(s -> s.get(2).getName().equals(BOOK_3_NAME));
    }
}
