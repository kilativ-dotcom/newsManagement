package com.example.news.exception;

public class NewsNotFoundException extends RuntimeException{

    private final Long id;

    public NewsNotFoundException(Long id) {
        super(id + " not found");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
