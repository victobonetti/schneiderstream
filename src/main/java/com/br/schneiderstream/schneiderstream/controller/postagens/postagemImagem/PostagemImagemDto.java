package com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem;
public record PostagemImagemDto(
    String alt,
    String url
) {
    public PostagemImagemDto(PostagemImagem postagemImagem){
        this(postagemImagem.getAlt(),postagemImagem.getUrl());
    }
}
