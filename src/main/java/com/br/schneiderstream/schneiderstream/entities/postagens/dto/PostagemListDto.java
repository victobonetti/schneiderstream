package com.br.schneiderstream.schneiderstream.entities.postagens.dto;
import com.br.schneiderstream.schneiderstream.entities.postagemImagem.PostagemImagemDto;
import com.br.schneiderstream.schneiderstream.entities.postagens.classes.Postagem;
import com.br.schneiderstream.schneiderstream.entities.users.dto.UserListDto;

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
