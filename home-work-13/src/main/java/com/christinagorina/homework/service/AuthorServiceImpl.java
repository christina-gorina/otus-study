package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.AuthorRepository;
import com.christinagorina.homework.domain.Author;
import com.christinagorina.homework.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    @Transactional
    @Override
    public Author getOrCreateByName(String name, String bookName) {
        Author author = authorRepository.findByName(name);
        if (Objects.nonNull(author)) {
            return author;
        }

        List<String> bookNames = new ArrayList<>();
        bookNames.add(bookName);

        return authorRepository.save(new Author(null, name, bookNames));
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getBooksByAuthorName(String name) {
        Author genre = authorRepository.findByName(name);
        return Optional.ofNullable(genre).map(Author::getBooksNames).orElse(Collections.emptyList());
    }
}
