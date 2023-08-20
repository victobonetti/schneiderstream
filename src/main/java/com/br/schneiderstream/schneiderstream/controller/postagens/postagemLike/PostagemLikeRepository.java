package com.br.schneiderstream.schneiderstream.controller.postagens.postagemLike;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemLikeRepository extends JpaRepository<PostagemLike, Integer> {
    List<PostagemLike> findAllByPostagemId(int postagemId);

    boolean existsByPostagemIdAndUserId(int postagemId, int userId);

    void deleteByUserId(int userId);
}
