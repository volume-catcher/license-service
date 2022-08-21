package com.teamdev.licenseservice.exception;

public class DuplicatedException extends RuntimeException {

    private static final long serialVersionUID = -821532049908897250L;

    public DuplicatedException(ErrorMessage message) {
        super(message.getMessage());
    }
}
