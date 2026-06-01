package com.java.app.hw43.security;

import com.java.app.hw43.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecUser implements UserDetails {
    private final String username;
    private final String password;

    public SecUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // no roles needed
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public static UserDetails formUser(User user) {
        return new SecUser(user.getEmail(), user.getPassword());
    }
}