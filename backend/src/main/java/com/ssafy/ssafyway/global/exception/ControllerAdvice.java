package com.ssafy.ssafyway.global.exception;

import com.ssafy.ssafyway.global.exception.dto.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponse> handleException(GlobalException e) {
        return ResponseEntity.status(e.getStatusCode()).body(ExceptionResponse.from(e));
    }
}
