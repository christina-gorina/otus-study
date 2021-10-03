package com.christinagorina.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {

 public Long id;

 public String name;

 public String genre;

 private List<Author> authors;

}
