package com.teamdev.licenseservice.exception;

import lombok.Getter;

@Getter
public class NotFoundAccountException extends RuntimeException {

    private static final long serialVersionUID = 8848836075056727771L;
    private String id;

    public NotFoundAccountException(String id) {
        this.id = id;
    }
}
