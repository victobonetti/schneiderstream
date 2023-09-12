package com.br.schneiderstream.schneiderstream.entities.postagemLike;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface PostagemLikeRepository extends JpaRepository<PostagemLike, Integer> {
    List<PostagemLike> findAllByPostagemId(int postagemId);

    boolean existsByPostagemIdAndUserId(int postagemId, int userId);

    void deleteByUserId(int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PostagemLike pl WHERE pl.userId = :userId AND pl.postagemId = :postagemId")
    void deleteByUserIdAndPostagemId(@Param("userId") int userId, @Param("postagemId") int postagemId);

}
 