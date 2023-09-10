package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

public record PostagemComentarioDto(
                int id,
                int userId,
                int postagemId,
                String conteudo) {

        public PostagemComentarioDto(PostagemComentario comentario) {
                this(comentario.getId(), comentario.getUserId(), comentario.getPostagemId(), comentario.getConteudo());
        }

}
