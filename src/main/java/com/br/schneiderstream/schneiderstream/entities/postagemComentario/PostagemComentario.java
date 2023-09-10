package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "portagemcomentarios")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
public class PostagemComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int postagemId;
    private String conteudo;

    public PostagemComentario(PostagemComentarioDto comentario) {
        this.userId = comentario.userId();
        this.postagemId = comentario.postagemId();
        this.conteudo = comentario.conteudo();
    }

    
}
