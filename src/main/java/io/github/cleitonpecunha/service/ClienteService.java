package io.github.cleitonpecunha.service;

import io.github.cleitonpecunha.domain.entity.Cliente;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Cliente excluir(Integer id);

    Cliente modificar(Integer id, Cliente cliente);

    Cliente buscar(Integer id);
}
