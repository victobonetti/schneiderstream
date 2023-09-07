package com.br.schneiderstream.schneiderstream.entities.postagens.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.schneiderstream.schneiderstream.entities.postagens.classes.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Integer> {}
