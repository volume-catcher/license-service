package com.teamdev.licenseservice.handler;

import com.teamdev.licenseservice.exception.*;
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
    public ResponseEntity<ErrorResponse> handleNotFoundAccountException(HttpServletRequest req, NotFoundAccountException e) {
        final ErrorCode errorCode = ErrorCode.ACCOUNT_NOT_FOUND;
        logger.debug("{} id: {}", errorCode.getMessage(), e.getId());
        return getErrorResponseEntity(req, errorCode);
    }

    @ExceptionHandler(DuplicateAccountException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateAccountException(HttpServletRequest req, DuplicateAccountException e) {
        final ErrorCode errorCode = ErrorCode.ACCOUNT_DUPLICATED;
        logger.debug("{}, id: {}", e.getMessage(), e.getId());
        return getErrorResponseEntity(req, errorCode);
    }

    @ExceptionHandler(NotFoundProductException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundProductException(HttpServletRequest req, NotFoundProductException e) {
        final ErrorCode errorCode = ErrorCode.PRODUCT_NOT_FOUND;
        logger.debug("{} id: {}", errorCode.getMessage(), e.getName());
        return getErrorResponseEntity(req, errorCode);
    }

    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateProductException(HttpServletRequest req, DuplicateProductException e) {
        final ErrorCode errorCode = ErrorCode.PRODUCT_DUPLICATED;
        logger.debug("{}, id: {}", e.getMessage(), e.getName());
        return getErrorResponseEntity(req, errorCode);
    }

    @ExceptionHandler(NotFoundLicenseException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundLicenseException(HttpServletRequest req, NotFoundLicenseException e) {
        final ErrorCode errorCode = ErrorCode.LICENSE_NOT_FOUND;
        logger.debug("{} id: {}", errorCode.getMessage(), e.getKey());
        return getErrorResponseEntity(req, errorCode);
    }

    @ExceptionHandler(DuplicateLicenseProductException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateLicenseProductException(HttpServletRequest req, DuplicateLicenseProductException e) {
        final ErrorCode errorCode = ErrorCode.LICENSEPRODUCT_DUPLICATED;
        logger.debug("{}, license: {}, product: {}", e.getMessage(), e.getLicenseKey(), e.getProductName());
        return getErrorResponseEntity(req, errorCode);
    }

    @ExceptionHandler(NotFoundLicenseProductException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundLicenseProductException(HttpServletRequest req, NotFoundLicenseProductException e) {
        final ErrorCode errorCode = ErrorCode.LICENSEPRODUCT_NOT_FOUND;
        logger.debug("{}, license: {}, product: {}", e.getMessage(), e.getLicenseKey(), e.getProductName());
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
