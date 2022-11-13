package com.teamdev.licenseservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    
    ACCOUNT_NOT_FOUND("계정을 찾을 수 없습니다"),
    ACCOUNT_DUPLICATED("이미 존재하는 계정입니다"),
    PRODUCT_NOT_FOUND("제품을 찾을 수 없습니다"),
    PRODUCT_DUPLICATED("이미 존재하는 제품입니다"),
    LICENSE_NOT_FOUND("라이선스를 찾을 수 없습니다"),
    CONTRACT_NOT_FOUND("계약을 찾을 수 없습니다"),
    CONTRACT_DUPLICATED("이미 존재하는 계약입니다"),

    FORBIDDEN("잘못된 요청입니다"),
    ;

    private final String message;
}
