package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.GenreRepository;
import com.christinagorina.homework.domain.Book;
import com.christinagorina.homework.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public Genre getOrCreateByName(String name, String bookName, String bookYear) {
        Genre genre = genreRepository.findByName(name);
        if (Objects.nonNull(genre)) {
            return genre;
        }
        List<Book> books = new ArrayList<>();
        Book book = new Book(null, bookName, null, bookYear , null, null, null);
        books.add(book);
        return genreRepository.save(new Genre(null, name, books));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByGenreName(String name) {
        Genre genre = genreRepository.findByName(name);
        return Optional.ofNullable(genre).map(Genre::getBooks).orElse(Collections.emptyList());
    }

}
