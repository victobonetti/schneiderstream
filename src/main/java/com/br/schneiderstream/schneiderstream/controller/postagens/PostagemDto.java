package com.br.schneiderstream.schneiderstream.controller.postagens;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostagemDto(
        @NotBlank String titulo,
        @NotNull int userId,
        @NotBlank String conteudo
     
        
        ) {
    public PostagemDto(
        Postagem postagem
    ) {
        this(postagem.getConteudo(), postagem.getUserId(), postagem.getConteudo());
    }
}
