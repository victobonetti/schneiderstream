package com.br.schneiderstream.schneiderstream.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleNotBadRequest(MethodArgumentNotValidException ex) {
        var e = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(e.stream().map(NotValidDto::new).toList().toString());
    }

    public record NotValidDto(String field, String defaultMessage) {
        public NotValidDto(FieldError err) {
            this(err.getField(), err.getDefaultMessage());
        }
    }

}
