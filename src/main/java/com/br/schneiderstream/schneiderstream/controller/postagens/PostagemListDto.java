package com.br.schneiderstream.schneiderstream.controller.postagens;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostagemListDto(
        @NotNull int id,
        @NotBlank String titulo,
        @NotBlank String userId,
        @NotBlank String conteudo,
        String imagem) {

            public PostagemListDto(Postagem postagem){
                this(postagem.getId(), postagem.getTitulo(), postagem.getUserId(), postagem.getConteudo(), postagem.getImagem());
            }
}
