package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByName(String username);

}