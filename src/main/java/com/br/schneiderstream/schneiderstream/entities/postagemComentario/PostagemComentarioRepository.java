package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemComentarioRepository extends JpaRepository<PostagemComentario, Integer> {

    List<PostagemComentario> findAllByPostagemId(int postId);


    }
