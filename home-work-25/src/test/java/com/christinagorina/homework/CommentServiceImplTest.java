package com.christinagorina.homework;

import com.christinagorina.homework.dao.BookDao;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import com.christinagorina.homework.service.CommentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.christinagorina.homework.TestData.NEW_COMMENT;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для CommentServiceImplTest")
@SpringBootTest
@Transactional
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Autowired
    private BookDao bookDao;

    @Test
    void createByText() {
        Optional<Book> bookOptional = bookDao.findById(1L);
        assertThat(bookOptional.get()).isNotNull();
        Comment actualComment = commentServiceImpl.createByText(NEW_COMMENT, bookOptional.get());
        assertThat(actualComment).isNotNull()
                .matches(s -> s.getText().equals(NEW_COMMENT));
    }

}
