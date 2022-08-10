package com.teamdev.licenseservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@ToString
public class ErrorResponse {

    private HttpStatus errorCode;
    private String ErrorMsg;
    private String requestURL;

    @Builder
    ErrorResponse(HttpStatus errorCode, String ErrorMsg, String requestURL) {
        this.errorCode = errorCode;
        this.ErrorMsg = ErrorMsg;
        this.requestURL = requestURL;
    }
}
