package com.br.schneiderstream.schneiderstream.dto;

public record MessageDto(
    String message
) {
    public MessageDto(String message){
        this.message = message;
    }
}
