package com.teamdev.licenseservice.exception;

import lombok.Getter;

@Getter
public class NotFoundLicenseProductException extends RuntimeException {

    private static final long serialVersionUID = -3816361457698707981L;
    private final String licenseKey;
    private final String productName;

    public NotFoundLicenseProductException(String licenseKey, String productName) {
        this.licenseKey = licenseKey;
        this.productName = productName;
    }
}
