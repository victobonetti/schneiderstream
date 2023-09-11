package com.br.schneiderstream.schneiderstream.entities.users.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.schneiderstream.schneiderstream.entities.users.classes.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String username);

    boolean existsByEmail(String email);

    // Correção da consulta SQL
    @Query(value = "SELECT id FROM users WHERE email = :email", nativeQuery = true)
    int getIdByEmail(@Param("email") String email);
}
