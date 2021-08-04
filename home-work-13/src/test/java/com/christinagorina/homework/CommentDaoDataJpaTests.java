package com.christinagorina.homework;

import com.christinagorina.homework.dao.CommentRepository;
import com.christinagorina.homework.domain.Comment;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.christinagorina.homework.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для CommentDaoDataJpa")
@DataMongoTest
public class CommentDaoDataJpaTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void updateComment() {
        val existingComment = mongoTemplate.findById("3", Comment.class);
        assertThat(existingComment).isNotNull();
        existingComment.setText(UPDATED_COMMENT1_TEXT);
        val expectedComment = commentRepository.save(existingComment);
        assertThat(expectedComment.getText()).isEqualTo(UPDATED_COMMENT1_TEXT);
    }

    @Test
    void createComment() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 8, 2, 6, 40, 45);
        val newComment = new Comment("1000", CREATED_COMMENT, "1", dateTime);
        val newCommentCreated = commentRepository.save(newComment);
        val expectedComment = mongoTemplate.findById(newCommentCreated.getId(), Comment.class);
        assertThat(expectedComment).usingRecursiveComparison().isEqualTo(newCommentCreated);
    }

    @Test
    void deleteById() {
        val comment = mongoTemplate.findById("2", Comment.class);
        assertThat(comment).isNotNull();
        commentRepository.delete(comment);
        val deletedGenre = mongoTemplate.findById("2", Comment.class);
        assertThat(deletedGenre).isNull();
    }

    @Test
    void getByIdComment() {
        Optional<Comment> actualComment = commentRepository.findById("1");
        assertThat(actualComment.get()).isNotNull()
                .matches(s -> s.getText().equals(COMMENT1))
                .matches(s -> s.getId().equals("1"));
    }

}
