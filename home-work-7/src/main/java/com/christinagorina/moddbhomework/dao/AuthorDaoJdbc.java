package com.christinagorina.moddbhomework.dao;

import com.christinagorina.moddbhomework.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final SimpleJdbcInsert insertAuthor;
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       JdbcTemplate jdbcTemplate) {

        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbcTemplate = jdbcTemplate;
        this.insertAuthor = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("author")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Author save(Author author) {
        MapSqlParameterSource authorMap = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("name", author.getName());

        if (author.isNew()) {
            Number authorKey = insertAuthor.executeAndReturnKey(authorMap);
            author.setId(authorKey.longValue());
        }else if (namedParameterJdbcOperations.update(
                "update author SET name=:name WHERE id=:id", authorMap) == 0) {
            return null;
        }
        return author;
    }

    @Override
    public boolean delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update(
                "delete from author where id = :id", params
        ) != 0;
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from author where id = :id", params, new AuthorDaoJdbc.AuthorMapper()
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

}
