package com.br.schneiderstream.schneiderstream.entities.postagens.dto;

import com.br.schneiderstream.schneiderstream.entities.postagemImagem.PostagemImagem;
import com.br.schneiderstream.schneiderstream.entities.postagemImagem.PostagemImagemDto;
import com.br.schneiderstream.schneiderstream.entities.postagens.classes.Postagem;

public record PostagemCreateDto(
    PostagemDto postagem,
    PostagemImagemDto imagem
) {
    public PostagemCreateDto(Postagem postagem, PostagemImagem imagem){
        this(new PostagemDto(postagem), new PostagemImagemDto(imagem));
    }
}
