package com.br.schneiderstream.schneiderstream.infra.security;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(User object) {
        System.out.println(secret);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000);
            String token = JWT.create()
                    .withIssuer("SCHNEIDERSTREAM")
                    .withSubject(object.getEmail())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
            return token; // Adicione esta linha para retornar o token gerado
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }
}
