package com.teamdev.licenseservice.handler;

import com.teamdev.licenseservice.exception.DuplicateAccountException;
import com.teamdev.licenseservice.exception.ErrorCode;
import com.teamdev.licenseservice.exception.ErrorResponse;
import com.teamdev.licenseservice.exception.NotFoundAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundAccountException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundAccountException(HttpServletRequest req,  NotFoundAccountException e) {
        logger.debug("계정을 찾을 수 없습니다, id: {}", e.getId());
        final ErrorCode errorCode = ErrorCode.ACCOUNT_NOT_FOUND;
        return getErrorResponseEntity(req, errorCode);
    }

    @ExceptionHandler(DuplicateAccountException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateAccountException(HttpServletRequest req, DuplicateAccountException e) {
        logger.debug("이미 존재하는 계정입니다, id: {}", e.getId());
        final ErrorCode errorCode = ErrorCode.ACCOUNT_DUPLICATED;
        return getErrorResponseEntity(req, errorCode);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseEntity(HttpServletRequest req, ErrorCode errorCode) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(errorCode.getHttpStatus())
                .ErrorMsg(errorCode.getMessage())
                .requestURL(req.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(errorResponse, errorResponse.getErrorCode());
    }
}
