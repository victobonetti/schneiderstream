package com.br.schneiderstream.schneiderstream.entities.postagemImagem;

public record PostagemImagemDto(
    String alt,
    String url,
    Integer postId
) {
    public PostagemImagemDto(PostagemImagem postagemImagem){
        this(postagemImagem.getAlt(),postagemImagem.getUrl(), postagemImagem.getPostId());
    }

}
