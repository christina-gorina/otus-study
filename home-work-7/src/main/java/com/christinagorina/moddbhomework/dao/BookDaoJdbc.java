package com.christinagorina.moddbhomework.dao;

import com.christinagorina.moddbhomework.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final SimpleJdbcInsert insertBook;
    private final JdbcTemplate jdbcTemplate;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       JdbcTemplate jdbcTemplate) {

        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbcTemplate = jdbcTemplate;
        this.insertBook = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("book")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Book save(Book book) {

        MapSqlParameterSource bookMap = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("name", book.getName())
                .addValue("authorId", book.getAuthorId())
                .addValue("genreId", book.getGenreId());

        if (book.isNew()) {
            Number bookKey = insertBook.executeAndReturnKey(bookMap);
            book.setId(bookKey.longValue());
        }else if (namedParameterJdbcOperations.update(
                "update book SET name=:name, author_id=:authorId, genre_id=:genreId " +
                        "WHERE id=:id", bookMap) == 0) {
            return null;
        }
        return book;
    }


    @Override
    public boolean delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        ) != 0;
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from book where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select * from book", new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            return new Book(id, name, authorId, genreId);
        }
    }
}
