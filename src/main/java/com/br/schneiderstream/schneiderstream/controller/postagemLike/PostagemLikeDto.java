package com.br.schneiderstream.schneiderstream.controller.postagemLike;

import jakarta.validation.constraints.NotNull;

public record PostagemLikeDto(
    @NotNull
    int userId,
    @NotNull
    int postagemId
) {

    public PostagemLikeDto(PostagemLike postagemLike){
        this(postagemLike.getUserId(), postagemLike.getPostagemId());
    }

}
