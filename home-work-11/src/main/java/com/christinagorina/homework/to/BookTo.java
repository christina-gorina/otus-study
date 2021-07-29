package com.christinagorina.homework.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTo {
    public Long id;

    public String name;

    public List<String> authorNames;

    public String genre;

    public List<String> comments;


}
