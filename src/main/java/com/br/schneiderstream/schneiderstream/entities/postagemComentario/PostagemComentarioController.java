package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens/comentarios")
public class PostagemComentarioController {

    @Autowired
    PostagemComentarioRepository repository;
    
    @GetMapping
    public List<PostagemComentarioDto> encontrarComentariosDaPostagem(@RequestParam(name = "id") int postId){
        return repository.findAllByPostagemId(postId).stream().map(PostagemComentarioDto::new).toList();
    }

    

}
