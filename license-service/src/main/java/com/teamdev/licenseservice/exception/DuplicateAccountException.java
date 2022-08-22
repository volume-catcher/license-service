package com.teamdev.licenseservice.exception;

public class DuplicateAccountException extends RuntimeException {

    private static final long serialVersionUID = 5426060279447423063L;
    private String id;

    public DuplicateAccountException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
