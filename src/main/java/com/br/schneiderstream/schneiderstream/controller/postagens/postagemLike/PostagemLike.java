package com.br.schneiderstream.schneiderstream.controller.postagens.postagemLike;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "postagemlikes")
public class PostagemLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int postagemId;

    public PostagemLike(
            PostagemLikeDto postagem) {
        this.postagemId = postagem.postagemId();
        this.userId = postagem.userId();
    }
}
