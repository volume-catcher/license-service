package com.teamdev.licenseservice.exception;

import lombok.Getter;

@Getter
public class DuplicateAccountException extends RuntimeException {

    private static final long serialVersionUID = 5426060279447423063L;
    private String id;

    public DuplicateAccountException(String id) {
        this.id = id;
    }
}
