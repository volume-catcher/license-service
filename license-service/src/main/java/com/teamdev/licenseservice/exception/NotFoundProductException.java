package com.teamdev.licenseservice.exception;

import lombok.Getter;

@Getter
public class NotFoundProductException extends RuntimeException {

    private static final long serialVersionUID = -8774907132389658273L;
    private String name;

    public NotFoundProductException(String name) {
        this.name = name;
    }
}