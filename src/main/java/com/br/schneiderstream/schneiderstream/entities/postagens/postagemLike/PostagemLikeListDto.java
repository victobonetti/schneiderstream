package com.br.schneiderstream.schneiderstream.entities.postagens.postagemLike;
import java.util.List;

import com.br.schneiderstream.schneiderstream.entities.users.dto.UserDto;

public record PostagemLikeListDto(
    List<UserDto> lista,
    int postagemId,
    int totalLikes
) {
    
}
