package com.example.news.exception;

public class ValidationException extends Exception{
    private final String field;

    public ValidationException(String field) {
        this.field = field;
    }

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
    }

    public ValidationException(String message, Throwable cause, String field) {
        super(message, cause);
        this.field = field;
    }

    public ValidationException(Throwable cause, String field) {
        super(cause);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
