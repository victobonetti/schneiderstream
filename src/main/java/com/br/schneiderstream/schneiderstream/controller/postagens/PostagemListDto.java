package com.br.schneiderstream.schneiderstream.controller.postagens;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagemDto;
import com.br.schneiderstream.schneiderstream.controller.users.UserListDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostagemListDto(
        @NotNull int id,
        @NotBlank String titulo,
        @NotBlank String conteudo,
        PostagemImagemDto imagem, //external
        @NotBlank UserListDto user //external
        ) {

            public PostagemListDto(Postagem postagem, PostagemImagemDto imagem, UserListDto user){
                this(postagem.getId(), postagem.getTitulo(), postagem.getConteudo(), imagem, user);
            }
}
