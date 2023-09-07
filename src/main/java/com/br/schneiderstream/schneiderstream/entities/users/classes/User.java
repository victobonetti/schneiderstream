package com.br.schneiderstream.schneiderstream.entities.users.classes;

import com.br.schneiderstream.schneiderstream.entities.users.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private int score;
    @Enumerated(EnumType.STRING)
    private Title title;
    private String foto;
    private String descricao;
    private String cargo;
   

    public User(UserDto json) {
        this.nome = json.nome();
        this.idade = json.idade();
        this.idade = json.idade();
        this.email = json.email();
        this.senha = json.senha();
        this.score = json.score();
        this.title = json.title();
        this.foto = json.foto();
        this.descricao = json.descricao();
        this.cargo = json.cargo();
    }

}
