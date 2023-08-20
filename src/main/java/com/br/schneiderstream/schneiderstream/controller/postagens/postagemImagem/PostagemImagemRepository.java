package com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemImagemRepository extends JpaRepository<PostagemImagem, Integer> {

    Optional<PostagemImagem> findByPostId(int postId);

}
