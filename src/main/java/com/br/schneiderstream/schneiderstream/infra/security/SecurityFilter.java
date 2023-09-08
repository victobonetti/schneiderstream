package com.br.schneiderstream.schneiderstream.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.schneiderstream.schneiderstream.entities.users.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("First filter.");
        var tkn = getToken(request);

        if (tkn != null) {
            var subject = service.getSubject(tkn);
            var usuario = repository.findByEmail(subject);
            System.out.println("Token:" + tkn);
            System.out.println("Token não é nulo. Subject: " + subject);
            System.out.println("Usuario: " + usuario);
            System.out.println("Autorities: " + usuario.getAuthorities());
            var auth = new UsernamePasswordAuthenticationToken(usuario, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("Auth: " + auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var tkn = request.getHeader("Authorization");
        if (tkn == null) {
            return null;
        } else {
            return tkn.replace("Bearer ", "").trim();
        }
    }

}
