package com.christinagorina.homework.service;

import com.christinagorina.homework.dao.UserDao;
import com.christinagorina.homework.domain.Role;
import com.christinagorina.homework.domain.SystemUser;
import com.christinagorina.homework.security.AuthorizedUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userDao.findByName(username);

        if (systemUser == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }

        return User.builder()
                .username(systemUser.getName())
                .password(systemUser.getPassword())
                .roles(systemUser.getRoles().stream().map(Role::getAuthority).collect(Collectors.joining()))
                .build();

    }

}