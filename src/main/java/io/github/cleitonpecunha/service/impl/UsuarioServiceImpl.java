package io.github.cleitonpecunha.service.impl;

import io.github.cleitonpecunha.domain.entity.Usuario;
import io.github.cleitonpecunha.domain.repository.Usuarios;
import io.github.cleitonpecunha.exception.RegistroDuplicadoException;
import io.github.cleitonpecunha.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private Usuarios usuariosRepository;

    @Transactional
    public Usuario salvar( Usuario usuario ) {

        if( usuariosRepository.existsByLogin(usuario.getLogin())) {
            throw new RegistroDuplicadoException("Login já cadastrado anteriormente");
        }
        return usuariosRepository.save(usuario);
    }

    public UserDetails autenticar( Usuario usuario ) {

        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

        //System.out.println(user.getAuthorities());

        if (senhasBatem) {
            return user;
        }
        throw new SenhaInvalidaException("Senha do usuário informada é inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuariosRepository
                .findByLogin(username)
                .orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado na base."));

        //System.out.println("admin?:"+usuario.isAdmin());

        String[] roles = usuario.isAdmin()
                ? new String[]{"ADMIN","USER"}
                : new String[]{"USER"};

        //System.out.println("roles:"+roles);

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
