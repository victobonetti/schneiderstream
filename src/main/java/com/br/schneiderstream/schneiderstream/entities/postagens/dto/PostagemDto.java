package com.br.schneiderstream.schneiderstream.entities.postagens.dto;

import com.br.schneiderstream.schneiderstream.entities.postagens.classes.Postagem;

import jakarta.validation.constraints.NotBlank;

public record PostagemDto(
        @NotBlank String titulo,
        @NotBlank String conteudo
     
        
        ) {
    public PostagemDto(
        Postagem postagem
    ) {
        this(postagem.getConteudo(), postagem.getConteudo());
    }
}
