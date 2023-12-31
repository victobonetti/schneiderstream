package com.br.schneiderstream.schneiderstream.entities.postagens.classes;

import com.br.schneiderstream.schneiderstream.entities.postagens.dto.PostagemDto;

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

@Table(name = "postagens")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private int userId;
    private String conteudo;

    public Postagem(PostagemDto json, int userId){
        this.titulo = json.titulo();
        this.userId = userId;
        this.conteudo = json.conteudo();
    }
}
