package com.br.schneiderstream.schneiderstream.entities.postagemLike;

import jakarta.validation.constraints.NotNull;

public record PostagemLikeCreateDto(
    @NotNull
    int id,
    @NotNull
    String tkn
) {

    public PostagemLikeCreateDto(PostagemLike postagemLike, String tkn){
        this(postagemLike.getUserId(), tkn);
    }

}
