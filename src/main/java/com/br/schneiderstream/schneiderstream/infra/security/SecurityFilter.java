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
        var tkn = getToken(request);
        if (tkn != null) {
            var subject = service.getSubject(tkn);
            var usuario = repository.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null,
                    usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var tkn = request.getHeader("Authorization");
        System.out.println("token_len: " + tkn.length() + ", token: '" + tkn +"'");
        if (tkn == null || tkn.contains("undefined")) {
            return null;
        } else {
            return tkn.replace("Bearer ", "").trim();
        }
    }

}
