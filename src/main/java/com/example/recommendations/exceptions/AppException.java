package com.example.recommendations.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


public class AppException extends RuntimeException {

    private final HttpStatus code;
    public AppException(String message, HttpStatus code) {

        super(message);
        this.code = code;

    }

    public HttpStatus getCode() {
        return code;
    }
}
