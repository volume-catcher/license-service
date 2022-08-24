package com.teamdev.licenseservice.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6673382524827278732L;

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
