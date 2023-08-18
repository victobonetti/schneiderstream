package com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens/imagem")
public class PostagemImagemController {

    @Autowired
    PostagemImagemRepository repository;

    @GetMapping("/{imgId}")
    public PostagemImagemDto findById(@RequestParam int imgId){
    PostagemImagem img = repository.findById(imgId).orElse(null);
    if (img == null){
        throw new Error("ID de imagem inválido.");
    }
    return new PostagemImagemDto(img);
    }
}
