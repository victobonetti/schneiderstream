package com.br.schneiderstream.schneiderstream.entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.schneiderstream.schneiderstream.infra.security.TokenService;

@Service
public class ActiveUserService {

    @Autowired
    TokenService service;

    public int getActiveUserId(String tkn) {
        return Integer.parseInt(service.getIdFromToken(tkn));
    }

}
