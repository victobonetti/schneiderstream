package com.br.schneiderstream.schneiderstream.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleNotBadRequest(MethodArgumentNotValidException ex) {
        var e = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(e.stream().map(NotValidDto::new).toList());
    }

    // @ExceptionHandler(MethodNotAllowedException.class)
    // public ResponseEntity handleMethodNotAllowedRequest(MethodNotAllowedException ex) {
    //     String mensagemDeErro = "Método não permitido para esta solicitação.";
    //     return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(mensagemDeErro);
    // }

    private record NotValidDto(String field, String defaultMessage) {
        public NotValidDto(FieldError err) {
            this(err.getField(), err.getDefaultMessage());
        }
    }



}
