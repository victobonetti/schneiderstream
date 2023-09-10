package com.br.schneiderstream.schneiderstream.entities.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.schneiderstream.schneiderstream.entities.users.classes.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String username);
    boolean existsByEmail(String email);
}
