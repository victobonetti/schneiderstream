package com.br.schneiderstream.schneiderstream.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager auth;
    
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthDto dados){
        var token = new UsernamePasswordAuthenticationToken(dados.nome(), dados.senha());
        var authentication = auth.authenticate(token);
        return ResponseEntity.ok().build();
    }

}
