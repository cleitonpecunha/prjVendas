package io.github.cleitonpecunha.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "usuario")

public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotEmpty(message = "{campo.login.usuario.obrigatorio}")
    private String login;

    @Column
    @NotEmpty(message = "{campo.senha.usuario.obrigatorio}")
    private String senha;

    @Column(name = "administrador")
    private boolean admin;
}
