package io.github.cleitonpecunha.domain.repository;

import io.github.cleitonpecunha.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Usuarios extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

    boolean existsByLogin(String login);

}
