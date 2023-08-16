package com.br.schneiderstream.schneiderstream.controller.postagens;

import jakarta.validation.constraints.NotBlank;

public record PostagemDto(
        @NotBlank String titulo,
        @NotBlank String userId,
        @NotBlank String conteudo,
        String imagem
        
        ) {
    public PostagemDto(
        Postagem postagem
    ) {
        this(postagem.getConteudo(), postagem.getUserId(), postagem.getConteudo(), postagem.getImagem());
    }
}
