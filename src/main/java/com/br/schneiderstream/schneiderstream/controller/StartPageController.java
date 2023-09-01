package com.br.schneiderstream.schneiderstream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StartPageController {

    @GetMapping
    public MessageDto sendWelcomeMessage(){
        return new MessageDto("Olá! Isso é uma mensagem de teste.");
    }

}
