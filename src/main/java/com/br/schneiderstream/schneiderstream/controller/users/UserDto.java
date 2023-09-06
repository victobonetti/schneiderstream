package com.br.schneiderstream.schneiderstream.controller.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        
        @NotBlank 
        String nome,
        @NotNull 
        int idade,
        @Email(regexp = ".+[@].+[\\.].+")
        @NotNull
        String email,
        @NotBlank
        String senha,
        @NotNull
        int score,
        @Valid
        @NotNull
        Title title,
        String foto,
        String descricao,
        String cargo
        ) {

    public UserDto(User user) {
        this(user.getNome(), user.getIdade(), user.getEmail(), user.getSenha(), user.getScore(), user.getTitle(), user.getFoto(), user.getDescricao(), user.getCargo());
    }
}