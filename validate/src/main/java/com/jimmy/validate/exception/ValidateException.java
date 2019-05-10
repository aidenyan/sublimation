package com.jimmy.validate.exception;

public class ValidateException extends RuntimeException {
    private String message;

    public ValidateException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
