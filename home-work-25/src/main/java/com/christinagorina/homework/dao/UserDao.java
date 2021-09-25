package com.christinagorina.homework.dao;

import com.christinagorina.homework.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<SystemUser, Long> {

    SystemUser findByName(String username);

}