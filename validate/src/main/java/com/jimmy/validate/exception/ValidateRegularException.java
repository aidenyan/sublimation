package com.jimmy.validate.exception;

public class ValidateRegularException extends RuntimeException {
    private String message;

    public ValidateRegularException(String message) {
        super(message);
        this.message = message;
    }

    public ValidateRegularException(Throwable e, String message) {
        super(message, e);
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
