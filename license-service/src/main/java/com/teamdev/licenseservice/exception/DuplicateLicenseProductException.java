package com.teamdev.licenseservice.exception;

import lombok.Getter;

@Getter
public class DuplicateLicenseProductException extends RuntimeException {

    private static final long serialVersionUID = 2705499753878186402L;
    private final String licenseKey;
    private final String productName;

    public DuplicateLicenseProductException(String licenseKey, String productName) {
        this.licenseKey = licenseKey;
        this.productName = productName;
    }
}
