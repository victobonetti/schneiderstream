package com.br.schneiderstream.schneiderstream.controller.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.schneiderstream.schneiderstream.controller.Id;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserContoller {

    //identifica o repository
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<Page<UserListDto>> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable p) {
        Page<UserListDto> page = repository.findAll(p).map(UserListDto::new);
        System.out.println(page);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/find")
    public ResponseEntity<User> encontraUsuario(@RequestParam int id){
        User user = repository.findById(id).orElse(null);
        if(user == null){
             return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Id> cadastro( @RequestBody @Valid UserDto json, UriComponentsBuilder ub){
        User newUser = repository.save(new User(json));
        Id id = new Id(newUser.getId());
        var uri = ub.path("/users/find").queryParam("id", id.id()).build().toUri();
        return ResponseEntity.created(uri).body(id);
    }
    
}
