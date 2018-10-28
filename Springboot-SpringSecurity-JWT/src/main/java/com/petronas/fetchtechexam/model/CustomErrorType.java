package com.petronas.fetchtechexam.model;

import org.springframework.http.HttpStatus;

public class CustomErrorType {
    private String errorMessage;
    private HttpStatus status;
    private String errorCode;
    // maybe add Object in here to optimized
    public CustomErrorType() {
    }

    public CustomErrorType(String errorMessage, HttpStatus status, String errorCode) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
