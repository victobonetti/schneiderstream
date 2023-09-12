package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

import com.br.schneiderstream.schneiderstream.entities.users.dto.UserListDto;

public record PostagemComentarioListDto(
                PostagemComentarioDto comentario,
                UserListDto user) {

        public PostagemComentarioListDto(PostagemComentario comentario, UserListDto user) {
                this(new PostagemComentarioDto(comentario), user);
        }

}
