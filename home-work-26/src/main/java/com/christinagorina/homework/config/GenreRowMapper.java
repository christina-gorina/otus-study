package com.christinagorina.homework.config;

import com.christinagorina.homework.domain.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Genre(rs.getLong("id"), rs.getString("name"), null);
    }
}
