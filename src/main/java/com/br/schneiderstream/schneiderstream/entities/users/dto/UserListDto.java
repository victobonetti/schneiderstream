package com.br.schneiderstream.schneiderstream.entities.users.dto;

import com.br.schneiderstream.schneiderstream.entities.users.classes.Title;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;

public record UserListDto (
        int id,
        String nome,
        int idade,
        String email,
        int score,
        Title title,
        String foto,
        String descricao,
        String cargo
){


    public UserListDto(User user){
        this(user.getId(), user.getNome(), user.getIdade(), user.getEmail(), user.getScore(), user.getTitle(), user.getFoto(), user.getCargo(), user.getCargo());
    }

}
