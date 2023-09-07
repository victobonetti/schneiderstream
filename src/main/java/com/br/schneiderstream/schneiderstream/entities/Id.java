package com.br.schneiderstream.schneiderstream.entities;

import jakarta.validation.constraints.NotNull;

public record Id(
    @NotNull
    int id
) {
    public Id(int id){
        this.id = id;
    }
}
