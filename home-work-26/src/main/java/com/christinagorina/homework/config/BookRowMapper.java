package com.christinagorina.homework.config;

import com.christinagorina.homework.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getLong("id"), rs.getString("name"),
                rs.getString("genre"), null);
    }

}