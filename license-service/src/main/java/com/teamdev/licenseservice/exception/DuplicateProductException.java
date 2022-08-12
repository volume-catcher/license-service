package com.teamdev.licenseservice.exception;

import lombok.Getter;

@Getter
public class DuplicateProductException extends RuntimeException{

    private static final long serialVersionUID = 7642987668304559589L;
    private String id;

    public DuplicateProductException(String id) {
        this.id = id;
    }
}
