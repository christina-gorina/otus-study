package com.christinagorina.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Author {

    public Long id;

    public String name;

    private List<Book> books;

}
