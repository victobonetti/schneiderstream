package com.br.schneiderstream.schneiderstream.controller;

import jakarta.validation.constraints.NotNull;

public record Id(
    @NotNull
    int id
) {
    public Id(int id){
        this.id = id;
    }
}
