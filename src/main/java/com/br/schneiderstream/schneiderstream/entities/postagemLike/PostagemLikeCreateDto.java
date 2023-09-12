package com.br.schneiderstream.schneiderstream.entities.postagemLike;

import jakarta.validation.constraints.NotNull;

public record PostagemLikeCreateDto(
    @NotNull
    int postagemId
) {

    public PostagemLikeCreateDto(PostagemLike postagemLike){
        this(postagemLike.getUserId());
    }

}
