package io.github.cleitonpecunha.service.impl;

import io.github.cleitonpecunha.domain.entity.Cliente;
import io.github.cleitonpecunha.domain.repository.Clientes;
import io.github.cleitonpecunha.exception.RegraNegocioException;
import io.github.cleitonpecunha.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ClienteServiceImpl implements ClienteService {

    private final Clientes clientesRepository;

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {
        if( clientesRepository.existsByCpf(cliente.getCpf()) ) {
            throw new RegraNegocioException("O CPF informado já se encontra cadastrado para outro cliente");
        }
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(cliente.getNome());
        novoCliente.setCpf(cliente.getCpf());
        clientesRepository.save(novoCliente);
        return novoCliente;
    }

    @Override
    @Transactional
    public Cliente excluir(Integer id) {
        Optional<Cliente> clienteO = clientesRepository.findById(id);
        if (!clienteO.isPresent())  {
            throw new RegraNegocioException("Cliente não encontrado para excluir!");
        }
        clientesRepository.delete(clienteO.get());
        return clienteO.get();
    }

    @Override
    @Transactional
    public Cliente modificar(Integer id, Cliente cliente) {
        Optional<Cliente> clienteO = clientesRepository.findById(id);
        if (!clienteO.isPresent())  {
            throw new RegraNegocioException("Cliente não encontrado para atualizar informações!");
        }
        cliente.setId(id);
        clientesRepository.save(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public Cliente buscar(Integer id) {
        Optional<Cliente> clienteO = clientesRepository.findById(id);
        if (!clienteO.isPresent())  {
            throw new RegraNegocioException("Cliente não encontrado!");
        }
        return clienteO.get();
    }

}
