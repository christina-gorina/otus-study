package com.christinagorina.homework.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTo {
    public String id;

    public String name;

    public String genre;

    public String year;

    public String language;

    public List<String> authorNames;

    public List<String> lastComments;

}
