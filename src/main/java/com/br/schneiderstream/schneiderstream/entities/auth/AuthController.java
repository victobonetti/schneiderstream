package com.br.schneiderstream.schneiderstream.entities.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.schneiderstream.schneiderstream.entities.users.classes.User;
import com.br.schneiderstream.schneiderstream.entities.users.repository.UserRepository;
import com.br.schneiderstream.schneiderstream.infra.security.TokenDto;
import com.br.schneiderstream.schneiderstream.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    TokenService tkn;

    @Autowired 
    UserRepository ur;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid AuthDto dados) {

        Integer userId = ur.getIdByEmail(dados.email());

        try {
            var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = auth.authenticate(token);
            var tokenJWT = tkn.gerarToken((User) authentication.getPrincipal(), userId.toString());
            return ResponseEntity.ok().body(new TokenDto(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Não foi possível efetuar login.");
        }
    }

}
