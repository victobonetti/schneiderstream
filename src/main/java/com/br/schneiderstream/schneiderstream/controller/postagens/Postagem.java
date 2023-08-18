package com.br.schneiderstream.schneiderstream.controller.postagens;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "postagens")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String userId;
    private String conteudo;
    private String imagemId;

    public Postagem(PostagemDto json){
        this.titulo = json.titulo();
        this.userId = json.userId();
        this.conteudo = json.conteudo();
        this.imagemId = json.imagemId();
    }
}
