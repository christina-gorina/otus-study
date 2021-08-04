package com.christinagorina.homework;

import com.christinagorina.homework.service.BookServiceImpl;
import com.christinagorina.homework.to.BookTo;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.ArrayList;
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
public class BookServiceImplTests {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Test
    void updateBook() {
        BookTo expectedBookTo = bookServiceImpl.findById("4");
        assertThat(expectedBookTo).isNotNull();

        expectedBookTo.setName(NEW_BOOK_NAME);
        expectedBookTo.setAuthorNames(Collections.singletonList(NEW_AUTHOR_NAME));
        expectedBookTo.setGenre(NEW_GENRE_NAME);
        List<String> lastComments = new ArrayList<>();
        lastComments.add(NEW_COMMENT_1);
        lastComments.add(NEW_COMMENT_2);
        lastComments.add(NEW_COMMENT_3);
        expectedBookTo.setLastComments(lastComments);
        BookTo actualBookTo = bookServiceImpl.update(expectedBookTo);

        assertThat(actualBookTo).isNotNull()
                .matches(s -> s.getId().equals("4"))
                .matches(s -> s.getName().equals(NEW_BOOK_NAME))
                .matches(s -> s.getGenre().equals(NEW_GENRE_NAME))
                .matches(s -> s.getLastComments().get(0).equals(NEW_COMMENT_3))
                .matches(s -> s.getLastComments().get(1).equals(NEW_COMMENT_2))
                .matches(s -> s.getLastComments().get(2).equals(NEW_COMMENT_1));
    }

    @Test
    void createBook() {
        BookTo actualBookTo = new BookTo(null, NEW_BOOK_NAME, NEW_GENRE_NAME,
                BOOK1_YEAR, BOOK1_LANGUAGE,
                Collections.singletonList(NEW_AUTHOR_NAME),
                Collections.singletonList(NEW_COMMENT));
        actualBookTo = bookServiceImpl.create(actualBookTo);
        BookTo expectedBookTo = bookServiceImpl.findById(actualBookTo.getId());
        assertThat(expectedBookTo).isNotNull();
        assertThat(actualBookTo).isNotNull()
                .matches(s -> s.getName().equals(NEW_BOOK_NAME))
                .matches(s -> s.getGenre().equals(NEW_GENRE_NAME))
                .matches(s -> s.getYear().equals(BOOK1_YEAR))
                .matches(s -> s.getLanguage().equals(BOOK1_LANGUAGE))
                .matches(s -> s.getLastComments().get(0).equals(NEW_COMMENT));
    }

    @Test
    void deleteBook() {

        val book = bookServiceImpl.findById("2");
        assertThat(book).isNotNull();
        bookServiceImpl.deleteById(book.getId());
        val deletedBook = bookServiceImpl.findById("2");
        assertThat(deletedBook).isNull();
    }

    @Test
    void getByIdBook() {
        BookTo actualBookTo = bookServiceImpl.findById("1");
        assertThat(actualBookTo).isNotNull();
        BookTo expectedBookTo = new BookTo("1", BOOK_1_NAME, GENRE_1_NAME, BOOK1_YEAR, BOOK1_LANGUAGE,
                Collections.singletonList(AUTHOR_1_NAME),
                Arrays.asList(COMMENT1, COMMENT2, COMMENT3));

        assertThat(actualBookTo).usingRecursiveComparison().isEqualTo(expectedBookTo);
    }

    @Test
    void getAllBook() {
        List<BookTo> actualBookList = bookServiceImpl.getAll();

        assertThat(actualBookList).isNotNull()
                .matches(s -> s.get(0).getId().equals("1"))
                .matches(s -> s.get(0).getName().equals(BOOK_1_NAME))
                .matches(s -> s.get(1).getId().equals("2"))
                .matches(s -> s.get(1).getName().equals(BOOK_2_NAME))
                .matches(s -> s.get(2).getId().equals("3"))
                .matches(s -> s.get(2).getName().equals(BOOK_3_NAME));
    }

    @Test
    void getLastCommentsByBook() {
        List<String> comments = bookServiceImpl.getLastCommentsByBook("1");
        assertThat(comments).isNotNull()
                .matches(s -> s.size() == 3)
                .matches(s -> s.get(0).equals(COMMENT1))
                .matches(s -> s.get(1).equals(COMMENT2))
                .matches(s -> s.get(2).equals(COMMENT3));
    }
}
