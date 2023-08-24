package com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem;

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

@Table(name = "postagem_imagem")
@Entity
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostagemImagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String alt;
    private Integer postId;

    public PostagemImagem(PostagemImagemDto imagem, int postId){
        this.url = imagem.url();
        this.alt = imagem.alt();
        this.postId = postId;
    }

}
