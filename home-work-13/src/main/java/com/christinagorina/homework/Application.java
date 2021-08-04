package com.christinagorina.homework;

import com.christinagorina.homework.dao.AuthorRepository;
import com.christinagorina.homework.dao.BookRepository;
import com.christinagorina.homework.dao.CommentRepository;
import com.christinagorina.homework.dao.GenreRepository;
import com.christinagorina.homework.domain.Genre;
import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
