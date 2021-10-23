package com.christinagorina.homework;

import com.christinagorina.homework.dao.CommentDao;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Comment;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для CommentDao")
@DataJpaTest
@ContextConfiguration
public class CommentDaoDataJpaTests {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void getByIdComment() {
        val actualComment = commentDao.findById(1L);
        assertThat(actualComment.get()).isNotNull()
                .matches(s -> s.getText().equals(COMMENT1))
                .matches(s -> s.getId() == 1L);
    }

    @Test
    void updateComment() {
        val existingComment = em.find(Comment.class, 1L);
        existingComment.setText(UPDATED_COMMENT1_TEXT);
        val expectedComment = commentDao.save(existingComment);
        assertThat(expectedComment.getText()).isEqualTo(UPDATED_COMMENT1_TEXT);
    }

    @Test
    void createComment() {
        Book book = em.find(Book.class, 1L);
        val newComment = new Comment(1000L, CREATED_COMMENT, book);
        val newCommentCreated = commentDao.save(newComment);
        val expectedComment = em.find(Comment.class, newCommentCreated.getId());
        assertThat(expectedComment).usingRecursiveComparison().isEqualTo(newCommentCreated);
    }

    @Test
    void deleteById() {
        val comment = em.find(Comment.class, 2L);
        assertThat(comment).isNotNull();
        commentDao.deleteById(2L);
        val deletedComment = em.find(Comment.class, 2L);
        assertThat(deletedComment).isNull();
    }
}
