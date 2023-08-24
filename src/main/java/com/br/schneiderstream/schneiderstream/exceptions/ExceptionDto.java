package com.br.schneiderstream.schneiderstream.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionDto(
    String message,
    HttpStatus status
) {
    
}
