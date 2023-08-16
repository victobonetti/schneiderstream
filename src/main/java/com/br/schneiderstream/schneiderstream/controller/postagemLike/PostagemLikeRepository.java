package com.br.schneiderstream.schneiderstream.controller.postagens.PostagemLike;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemLikeRepository extends JpaRepository<PostagemLike, Integer> {
    List<PostagemLike> findAllByPostagemId(int postagemId);
}
