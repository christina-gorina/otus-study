package com.christinagorina.homework.config;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor
public class JobConfig {
    private static final int CHUNK_SIZE = 2;

    public static final String IMPORT_LIBRARY_JOB_NAME = "importLibraryJob";

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final DataSource dataSource;

    private final MongoTemplate mongoTemplate;

    @StepScope
    @Bean
    public JdbcCursorItemReader<Book> bookReader() {

        return new JdbcCursorItemReaderBuilder<Book>()
                .sql("select b.id as id, b.name as name, g.name as genre from book as b " +
                        "join genre as g on b.genre_id = g.id")
                .name("bookJdbcCursorItemReader")
                .dataSource(dataSource)
                .rowMapper(new BookRowMapper())
                .build();

    }

    @StepScope
    @Bean
    public ItemProcessor<Book, Book> bookItemProcessor(MigrationService migrationService) {
        return migrationService::bookProcessing;
    }

    @StepScope
    @Bean
    public MongoItemWriter<Book> bookWriter() {
        MongoItemWriter<Book> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("book");
        return writer;
    }

    @Bean
    public Step bookStep(JdbcCursorItemReader<Book> bookReader, MongoItemWriter<Book> bookWriter,
                         ItemProcessor<Book, Book> bookItemProcessor) {
        return stepBuilderFactory.get("bookStep")
                .<Book, Book>chunk(CHUNK_SIZE)
                .reader(bookReader)
                .processor(bookItemProcessor)
                .writer(bookWriter)
                .build();
    }

    @StepScope
    @Bean
    public JdbcCursorItemReader<Genre> genreReader() {

        return new JdbcCursorItemReaderBuilder<Genre>()
                .sql("select g.id as id, g.name as name from genre as g")
                .name("genreJdbcCursorItemReader")
                .dataSource(dataSource)
                .rowMapper(new GenreRowMapper())
                .build();

    }

    @StepScope
    @Bean
    public ItemProcessor<Genre, Genre> genreItemProcessor(MigrationService migrationService) {
        return migrationService::genreProcessing;
    }

    @StepScope
    @Bean
    public MongoItemWriter<Genre> genreWriter() {
        MongoItemWriter<Genre> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("genre");
        return writer;
    }

    @Bean
    public Step genreStep(JdbcCursorItemReader<Genre> genreReader, MongoItemWriter<Genre> genreWriter,
                          ItemProcessor<Genre, Genre> genreItemProcessor) {
        return stepBuilderFactory.get("genreStep")
                .<Genre, Genre>chunk(CHUNK_SIZE)
                .reader(genreReader)
                .processor(genreItemProcessor)
                .writer(genreWriter)
                .build();
    }

    @StepScope
    @Bean
    public JdbcCursorItemReader<Author> authorReader() {

        return new JdbcCursorItemReaderBuilder<Author>()
                .sql("select a.id as id, a.name as name from author as a")
                .name("authorJdbcCursorItemReader")
                .dataSource(dataSource)
                .rowMapper(new AuthorRowMapper())
                .build();

    }

    @StepScope
    @Bean
    public ItemProcessor<Author, Author> authorItemProcessor(MigrationService migrationService) {
        return migrationService::authorProcessing;
    }

    @StepScope
    @Bean
    public MongoItemWriter<Author> authorWriter() {
        MongoItemWriter<Author> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("author");
        return writer;
    }



    @Bean
    public Step authorStep(JdbcCursorItemReader<Author> authorReader, MongoItemWriter<Author> authorWriter,
                           ItemProcessor<Author, Author> authorItemProcessor) {
        return stepBuilderFactory.get("genreStep")
                .<Author, Author>chunk(CHUNK_SIZE)
                .reader(authorReader)
                .processor(authorItemProcessor)
                .writer(authorWriter)
                .build();
    }

    @Bean
    public Job importLibraryJob(Step bookStep, Step genreStep, Step authorStep) {
        return jobBuilderFactory.get(IMPORT_LIBRARY_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(bookStep)
                .next(genreStep)
                .next(authorStep)
                .end()
                .build();
    }

}
