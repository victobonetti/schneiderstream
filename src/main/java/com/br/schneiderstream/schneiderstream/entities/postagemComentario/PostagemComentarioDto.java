package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

public record PostagemComentarioDto(
                int id,
                int postagemId,
                String conteudo) {

        public PostagemComentarioDto(PostagemComentario comentario) {
                this(comentario.getId(), comentario.getPostagemId(), comentario.getConteudo());
        }

}
