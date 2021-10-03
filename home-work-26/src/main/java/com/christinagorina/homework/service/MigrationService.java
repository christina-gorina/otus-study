package com.christinagorina.homework.service;

import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MigrationService {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public Book bookProcessing(Book book) {
        List<Author> authors = getAuthors(book.getId());
        book.setAuthors(authors);
        return book;
    }

    public Author authorProcessing(Author author) {
        List<Book> books = getBooksByAuthor(author.getId());
        author.setBooks(books);
        return author;
    }

    public Genre genreProcessing(Genre genre) {
        List<Book> books = getBooksByGenre(genre.getId());
        genre.setBooks(books);
        return genre;
    }

    private List<Book> getBooksByAuthor(Long authorId) {
        String sql = "select b.id as id, b.name as name from book_authors as ba " +
                "join book as b on ba.book_id = b.id " +
                "where ba.author_id =:authorId";

        Map<String, Object> params = Collections.singletonMap("authorId", authorId);

        return namedParameterJdbcOperations.query(sql, params,
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                null,
                                null
                        )
        );
    }

    private List<Book> getBooksByGenre(Long genreId) {
        String sql = "select b.id as id, b.name as name from book as b " +
                "where b.genre_id =:genreId";

        Map<String, Object> params = Collections.singletonMap("genreId", genreId);

        return namedParameterJdbcOperations.query(sql, params,
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                null,
                                null
                        )
        );
    }

    private List<Author> getAuthors(Long bookId) {

        String sql = "select a.id as id, a.name as name from book_authors as ba " +
                "join author as a on ba.author_id = a.id " +
                "where ba.book_id =:bookId";

        Map<String, Object> params = Collections.singletonMap("bookId", bookId);

        return namedParameterJdbcOperations.query(sql, params,
                (rs, rowNum) ->
                        new Author(
                                rs.getLong("id"),
                                rs.getString("name"),
                                null
                        )
        );
    }

}
