package com.teamdev.licenseservice.exception;

import lombok.Getter;

@Getter
public class NotFoundLicenseException extends RuntimeException {

    private static final long serialVersionUID = 2070344470584981924L;
    private final String key;

    public NotFoundLicenseException(String key) {
        this.key = key;
    }
}
