package com.br.schneiderstream.schneiderstream.controller.postagens;

import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagem;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagemDto;

public record PostagemCreateDto(
    PostagemDto postagem,
    PostagemImagemDto imagem
) {
    public PostagemCreateDto(Postagem postagem, PostagemImagem imagem){
        this(new PostagemDto(postagem), new PostagemImagemDto(imagem));
    }
}
