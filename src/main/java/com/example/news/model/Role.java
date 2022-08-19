package com.example.news.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, CREATE_NEWS, CHANGE_NEWS, DELETE_NEWS;

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public String toString() {
        return getAuthority();
    }
}
