package com.christinagorina.moddbhomework;

import com.christinagorina.moddbhomework.service.BookService;
import com.christinagorina.moddbhomework.to.BookTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.christinagorina.moddbhomework.TestData.*;
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
        expectedBookTo.setAuthor(NEW_AUTHOR_NAME);
        expectedBookTo.setGenre(NEW_GENRE_NAME);

        BookTo actualBookTo = bookService.update(expectedBookTo);
        assertThat(actualBookTo).usingRecursiveComparison().isEqualTo(expectedBookTo);
    }

    @Test
    void createBook() {
        BookTo actualBookTo = new BookTo(null, NEW_BOOK_NAME, NEW_AUTHOR_NAME, NEW_GENRE_NAME);
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
        BookTo expectedBookTo = new BookTo(1L, BOOK_1_NAME, AUTHOR_1_NAME, GENRE_1_NAME);
        assertThat(actualBookTo).usingRecursiveComparison().isEqualTo(expectedBookTo);
    }

    @Test
    void getAllBook() {
        List<BookTo> actualBookList = bookService.getAll();
        BookTo bookTo1 = new BookTo(1L, BOOK_1_NAME, AUTHOR_1_NAME, GENRE_1_NAME);
        BookTo bookTo2 = new BookTo(2L, BOOK_2_NAME, AUTHOR_2_NAME, GENRE_2_NAME);
        BookTo bookTo3 = new BookTo(3L, BOOK_3_NAME, AUTHOR_3_NAME, GENRE_3_NAME);

        List<BookTo> expectedBookList = new ArrayList<>();
        expectedBookList.add(bookTo1);
        expectedBookList.add(bookTo2);
        expectedBookList.add(bookTo3);

        assertThat(actualBookList).usingRecursiveComparison().isEqualTo(expectedBookList);
    }

}
