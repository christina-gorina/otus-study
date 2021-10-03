package com.christinagorina.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Genre {

    public Long id;

    public String name;

    public List<Book> books;

}
