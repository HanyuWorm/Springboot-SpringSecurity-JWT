package com.petronas.fetchtechexam.model;

import org.springframework.http.HttpStatus;

public class ResponseToken {
    private String JWTMessage;
    private HttpStatus status;
    private String message;

    public ResponseToken() {
    }

    public ResponseToken(String JWTMessage, HttpStatus status, String message) {
        this.JWTMessage = JWTMessage;
        this.status = status;
        this.message = message;
    }

    public String getJWTMessage() {
        return JWTMessage;
    }

    public void setJWTMessage(String JWTMessage) {
        this.JWTMessage = JWTMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
