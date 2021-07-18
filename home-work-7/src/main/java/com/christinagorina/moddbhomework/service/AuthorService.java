package com.christinagorina.moddbhomework.service;

import com.christinagorina.moddbhomework.dao.AuthorDao;
import com.christinagorina.moddbhomework.domain.Author;
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
