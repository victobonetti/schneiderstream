package com.br.schneiderstream.schneiderstream.entities.users.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.br.schneiderstream.schneiderstream.entities.Id;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;
import com.br.schneiderstream.schneiderstream.entities.users.dto.UserDto;
import com.br.schneiderstream.schneiderstream.entities.users.dto.UserListDto;
import com.br.schneiderstream.schneiderstream.entities.users.repository.UserRepository;
import com.br.schneiderstream.schneiderstream.infra.security.TokenService;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
// @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserContoller {

    // identifica o repository
    @Autowired
    private UserRepository repository;

    @Autowired 
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Page<UserListDto>> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable p) {
        Page<UserListDto> page = repository.findAll(p).map(UserListDto::new);
        System.out.println(page);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/find")
    public ResponseEntity<User> encontraUsuario(@RequestParam int id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/find")
    public User encontraUsuarioPorToken(@RequestBody String tkn){
        Optional<User> result = repository.findById(repository.getIdByEmail(tokenService.getSubject(tkn)));
        return result.get();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @Transactional
    @PostMapping
    public ResponseEntity<Id> cadastro(@RequestBody @Valid UserDto json, UriComponentsBuilder ub) {
        System.out.println("Cadastro acessado.");
        if (!repository.existsByEmail(json.email())) {
            User newUser = repository.save(new User(json));
            Id id = new Id(newUser.getId());
            var uri = ub.path("/users/find").queryParam("id", id.id()).build().toUri();
            return ResponseEntity.created(uri).body(id);
        } else {
            throw new RuntimeException("Email já cadastrado");
        }
    }
}
