package com.br.schneiderstream.schneiderstream.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    public  NotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
