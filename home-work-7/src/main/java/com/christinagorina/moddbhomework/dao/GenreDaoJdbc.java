package com.christinagorina.moddbhomework.dao;

import com.christinagorina.moddbhomework.domain.Genre;
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
public class GenreDaoJdbc implements GenreDao{

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final SimpleJdbcInsert insertGenre;
    private final JdbcTemplate jdbcTemplate;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       JdbcTemplate jdbcTemplate) {

        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbcTemplate = jdbcTemplate;
        this.insertGenre = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("genre")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Genre save(Genre genre) {
        MapSqlParameterSource genreMap = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("name", genre.getName());

        if (genre.isNew()) {
            Number genreKey = insertGenre.executeAndReturnKey(genreMap);
            genre.setId(genreKey.longValue());
        }else if (namedParameterJdbcOperations.update(
                "update genre SET name=:name WHERE id=:id", genreMap) == 0) {
            return null;
        }
        return genre;
    }

    @Override
    public boolean delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update(
                "delete from genre where id = :id", params
        ) != 0;
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from genre where id = :id", params, new GenreDaoJdbc.GenreMapper()
        );
    }

    @Override
    public Genre getOrCreateByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.query(
                "select id, name from genre where name = :name", params, rs -> {
                    if (!rs.next())
                        return save(new Genre(null, name));

                    return new Genre(rs.getLong("id"), rs.getString("name"));
                });
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
