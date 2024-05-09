package com.railsware.mailtrap.exception;

public class APIException extends Exception {

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable ex) {
        super(message, ex);
    }
}