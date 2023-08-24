package com.br.schneiderstream.schneiderstream.controller;

public record MessageDto(
    String message
) {
    public MessageDto(String message){
        this.message = message;
    }
}
