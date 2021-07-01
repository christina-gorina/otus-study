package com.christinagorina.moddbhomework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {

    public Long id;

    public String name;

    public long authorId;

    public long genreId;

    public boolean isNew() {
        return this.id == null;
    }

}