package io.github.cleitonpecunha.service;

import io.github.cleitonpecunha.domain.entity.Pedido;
import io.github.cleitonpecunha.domain.entity.Produto;
import io.github.cleitonpecunha.domain.enums.StatusPedido;
import io.github.cleitonpecunha.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto( Integer id );

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
