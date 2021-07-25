package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.AuthorDao;
import com.christinagorina.homework.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorDao authorDao;

    public Author getOrCreateByName(String name) {
        return authorDao.getOrCreateByName(name);
    }
}
