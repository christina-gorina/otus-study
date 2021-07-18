package com.christinagorina.moddbhomework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    public Long id;

    public String name;

    public Author author;

    public Genre genre;

    public boolean isNew() {
        return this.id == null;
    }

}