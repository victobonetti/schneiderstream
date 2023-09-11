package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.schneiderstream.schneiderstream.entities.ActiveUserService;
import com.br.schneiderstream.schneiderstream.entities.Id;

@RestController
@RequestMapping("/postagens/comentarios")
public class PostagemComentarioController {

    @Autowired
    PostagemComentarioRepository repository;

    @Autowired
    ActiveUserService userDataService;
    
    @GetMapping
    public List<PostagemComentarioDto> encontrarComentariosDaPostagem(@RequestParam(name = "id") int postId){
        return repository.findAllByPostagemId(postId).stream().map(PostagemComentarioDto::new).toList();
    }

    @PostMapping
    public Id criarComentario(@RequestBody PostagemComentarioDto comentario){    
        System.out.println(userDataService.getActiveUserData());
        return new Id(repository.save(new PostagemComentario(comentario)).getId());
    }

}
