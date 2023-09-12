package com.br.schneiderstream.schneiderstream.entities.postagemLike;
import java.util.List;

import com.br.schneiderstream.schneiderstream.entities.users.dto.UserListDto;

public record PostagemLikeListDto(
    List<UserListDto> lista,
    int postagemId,
    int totalLikes
) {
    
}
