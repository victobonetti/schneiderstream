package com.br.schneiderstream.schneiderstream.entities.users.classes;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.schneiderstream.schneiderstream.entities.users.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private int score;
    @Enumerated(EnumType.STRING)
    private Title title;
    private String foto;
    private String descricao;
    private String cargo;

    public User(UserDto json) {
        this.nome = json.nome();
        this.idade = json.idade();
        this.idade = json.idade();
        this.email = json.email();
        this.senha = hashPassword(json.senha());
        this.score = json.score();
        this.title = json.title();
        this.foto = json.foto();
        this.descricao = json.descricao();
        this.cargo = json.cargo();
    }

    private String hashPassword(String plainPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(plainPassword);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha; // Return the actual password stored in the User object
    }

    @Override
    public String getUsername() {
        return this.email; // Return the email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implement your logic here, return true or false based on your business rules
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement your logic here, return true or false based on your business rules
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement your logic here, return true or false based on your business rules
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Implement your logic here, return true or false based on your business rules
        return true;
    }

}
