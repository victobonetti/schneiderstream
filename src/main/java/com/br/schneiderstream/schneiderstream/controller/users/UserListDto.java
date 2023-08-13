package com.br.schneiderstream.schneiderstream.controller.users;

public record UserListDto (
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
        this(user.getNome(), user.getIdade(), user.getEmail(), user.getScore(), user.getTitle(), user.getFoto(), user.getCargo(), user.getCargo());
    }

}
