package com.example.fichaje.infrastructure.rest;


import com.example.fichaje.application.exceptions.ClockInTypeNotFoundException;
import com.example.fichaje.infrastructure.rest.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        FieldError error = ex.getBindingResult().getFieldErrors().getFirst();
        ErrorResponse body = ErrorResponse.builder()
                .error(error.getField() + ": " + error.getDefaultMessage())
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ClockInTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClockInTypeNotFoundException(ClockInTypeNotFoundException ex){
        ErrorResponse body = ErrorResponse.builder()
                .error(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body);
    }
}
