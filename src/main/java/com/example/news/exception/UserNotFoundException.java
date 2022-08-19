package com.example.news.exception;

public class UserNotFoundException extends Exception{
    private final String username;

    public UserNotFoundException(String username) {
        this.username = username;
    }

    public UserNotFoundException(String message, String username) {
        super(message);
        this.username = username;
    }

    public UserNotFoundException(String message, Throwable cause, String username) {
        super(message, cause);
        this.username = username;
    }

    public UserNotFoundException(Throwable cause, String username) {
        super(cause);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
