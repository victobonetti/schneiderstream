package com.br.schneiderstream.schneiderstream.infra.security;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String getSubject(String tkn) {
        System.out.println("Subject!");
        System.out.println(tkn);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var subject = JWT.require(algorithm)
                    .withIssuer("SCHNEIDERSTREAM")
                    .build()
                    .verify(tkn)
                    .getSubject();
            System.out.println(subject);
            return subject;

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Erro ao resgatar subject: " + exception.getCause());
        }
    }

    public String getIdFromToken(String tkn) {
        System.out.println("fIND ID FROM TOKEN!");
        System.out.println(tkn);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var id = JWT.require(algorithm)
                    .withIssuer("SCHNEIDERSTREAM")
                    .build()
                    .verify(tkn)
                    .getClaim("userId")
                    .asString();
            System.out.println(id);
            return id;
        } catch (JWTDecodeException exception) {
            throw new RuntimeException("Erro ao regatar id através de token", exception);
        }

    }

    public String gerarToken(User object, String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000);
            String token = JWT.create()
                    .withIssuer("SCHNEIDERSTREAM")
                    .withSubject(object.getEmail())
                    .withClaim("userId", userId)
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
            System.out.println("TOKEN!");
            System.out.println(token);
            return token; // Adicione esta linha para retornar o token gerado
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }
}
