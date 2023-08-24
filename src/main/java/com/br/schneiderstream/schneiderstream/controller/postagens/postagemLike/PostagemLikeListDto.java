package com.br.schneiderstream.schneiderstream.controller.postagens.postagemLike;
import java.util.List;

import com.br.schneiderstream.schneiderstream.controller.users.UserDto;

public record PostagemLikeListDto(
    List<UserDto> lista,
    int postagemId,
    int totalLikes
) {
    
}
