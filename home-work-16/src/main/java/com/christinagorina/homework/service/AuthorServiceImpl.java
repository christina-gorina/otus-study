package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.AuthorDao;
import com.christinagorina.homework.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public Author getOrCreateByName(String name) {
        Author author = authorDao.findByName(name);

        if (Objects.nonNull(author)) {
            return author;
        }
        return authorDao.save(new Author(null, name, null));
    }
}
