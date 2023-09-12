package com.br.schneiderstream.schneiderstream.entities.users.dto;

import com.br.schneiderstream.schneiderstream.entities.users.classes.Title;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;

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