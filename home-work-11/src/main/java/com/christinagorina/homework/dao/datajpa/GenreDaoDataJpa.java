package com.christinagorina.homework.dao.datajpa;

import com.christinagorina.homework.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreDaoDataJpa extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

    @Modifying
    @Query("delete from Genre b where b.id =:id")
    int deleteItem(@Param("id") long id);
}
