package com.christinagorina.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    public String id;

    public String name;

    public String genre;

    public String year;

    public String language;

    private List<Author> authors;

    private List<String> lastComments;

}
