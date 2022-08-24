package com.teamdev.licenseservice.exception;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 8867130574265525977L;

    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
