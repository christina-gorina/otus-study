package com.christinagorina.moddbhomework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Author {

    public Long id;

    public String name;

    public boolean isNew() {
        return this.id == null;
    }

}

