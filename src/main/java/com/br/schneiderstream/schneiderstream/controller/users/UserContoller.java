package com.br.schneiderstream.schneiderstream.controller.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.schneiderstream.schneiderstream.controller.Id;
import com.br.schneiderstream.schneiderstream.exceptions.NotFoundException;

import org.springframework.data.web.PageableDefault;
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
    public Page<UserListDto> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable p) {
        Page<UserListDto> page = repository.findAll(p).map(UserListDto::new);
        System.out.println(page);
        return page;
    }

    @GetMapping("/find")
    public User encontraUsuario(@RequestParam int id){
        User user = repository.findById(id).orElse(null);
        if(user == null){
            throw new NotFoundException("Id de usuário inválido.");
        }
        return user;
    }

    @Transactional
    @PostMapping
    public Id cadastro( @RequestBody @Valid UserDto json){
        User newUser = repository.save(new User(json));
        return new Id(newUser.getId());
    }
    
}
