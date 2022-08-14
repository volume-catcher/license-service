package com.teamdev.licenseservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "계정을 찾을 수 없습니다"),
    ACCOUNT_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 계정입니다"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "제품을 찾을 수 없습니다"),
    PRODUCT_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 제품입니다"),
    LICENSE_NOT_FOUND(HttpStatus.NOT_FOUND, "라이선스를 찾을 수 없습니다"),
    LICENSEPRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "라이선스-제품를 찾을 수 없습니다"),
    LICENSEPRODUCT_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 라이선스-제품입니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
