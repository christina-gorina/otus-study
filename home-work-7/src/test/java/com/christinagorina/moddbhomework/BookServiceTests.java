package com.christinagorina.moddbhomework;

import com.christinagorina.moddbhomework.domain.Book;
import com.christinagorina.moddbhomework.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    private static final String BOOK_1_NAME = "Sea story";
    private static final String BOOK_2_NAME = "Call of the night";
    private static final String BOOK_3_NAME = "Close your eyes";
    private static final String UPDATED_BOOK_NAME = "Updated book name";
    private static final String CREATED_BOOK_NAME = "Story about dolls";

    @Test
    void updateBook() {
        Book existingBook = bookService.getById(1);
        existingBook.setName(UPDATED_BOOK_NAME);
        Book expectedBook = bookService.update(existingBook);
        assertThat(expectedBook.getName()).isEqualTo(UPDATED_BOOK_NAME);
    }

    @Test
    void createBook() {
        Book newBook = new Book(null, CREATED_BOOK_NAME, 1, 1);
        newBook = bookService.create(newBook);
        Book expectedBook = bookService.getById(newBook.getId());
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(newBook);
    }

    @Test
    void deleteBook() {
        boolean isDeleted = bookService.delete(2);
        assertThat(isDeleted).isTrue();
    }

    @Test
    void getByIdBook() {
        Book actualBook = bookService.getById(1);
        Book expectedBook = new Book(1L, BOOK_1_NAME, 1, 1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getAllBook() {
        List<Book> actualBookList = bookService.getAll();
        Book book1 = new Book(1L, BOOK_1_NAME, 1, 1);
        Book book2 = new Book(2L, BOOK_2_NAME, 2, 2);
        Book book3 = new Book(3L, BOOK_3_NAME, 3, 3);

        List<Book> expectedBookList = new ArrayList<>();
        expectedBookList.add(book1);
        expectedBookList.add(book2);
        expectedBookList.add(book3);

        assertThat(actualBookList).usingRecursiveComparison().isEqualTo(expectedBookList);
    }

}
