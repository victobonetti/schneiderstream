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

@Table(name = "imagem")
@Entity
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostagemImagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String url;
    private String alt;

    public PostagemImagem(PostagemImagemDto postagemImagem){
        this.url = postagemImagem.url();
        this.alt = postagemImagem.alt();
    }


}
