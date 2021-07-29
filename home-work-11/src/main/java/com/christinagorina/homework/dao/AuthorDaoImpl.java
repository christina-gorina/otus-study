package com.christinagorina.homework.dao;

import com.christinagorina.homework.dao.datajpa.AuthorDaoDataJpa;
import com.christinagorina.homework.domain.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@AllArgsConstructor
@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorDaoDataJpa authorDaoDataJpa;

    @Override
    public Author save(Author author) {
        return authorDaoDataJpa.save(author);
    }

    @Override
    public boolean delete(long id) {
        return authorDaoDataJpa.deleteItem(id) != 0;
    }

    @Override
    public Author getById(long id) {
        return authorDaoDataJpa.getById(id);
    }

    @Override
    public Author getOrCreateByName(String name) {
        Author author = authorDaoDataJpa.findByName(name);

        if (Objects.nonNull(author)) {
            return author;
        }
        return save(new Author(null, name, null));
    }

}
