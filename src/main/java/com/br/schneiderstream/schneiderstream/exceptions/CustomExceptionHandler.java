package com.br.schneiderstream.schneiderstream.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException ex) {
        ExceptionDto bodyException = new ExceptionDto(ex.getMessage(), ex.getHttpStatus());
        return ResponseEntity.status(bodyException.status()).body(bodyException);
    }

    // Outros métodos de tratamento de exceção

}
