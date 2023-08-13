package io.github.cleitonpecunha.rest.controller;

import io.github.cleitonpecunha.domain.entity.ItemPedido;
import io.github.cleitonpecunha.domain.entity.Pedido;
import io.github.cleitonpecunha.domain.enums.StatusPedido;
import io.github.cleitonpecunha.rest.dto.AtualizaStatusPedidoDTO;
import io.github.cleitonpecunha.rest.dto.InformacaoItemPedidoDTO;
import io.github.cleitonpecunha.rest.dto.InformacaoPedidoDTO;
import io.github.cleitonpecunha.rest.dto.PedidoDTO;
import io.github.cleitonpecunha.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")

public class PedidoController {

    private PedidoService pedidoservice;

    public PedidoController(PedidoService pedidoservice) {
        this.pedidoservice = pedidoservice;
    }

    // Salvar os dados (Insert)
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ) {
        Pedido pedido = pedidoservice.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacaoPedidoDTO getById(@PathVariable Integer id ){
        return pedidoservice
                .obterPedidoCompleto(id)
                .map( p -> converter(p))
                .orElseThrow( () -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacaoPedidoDTO converter(Pedido pedido){
        return InformacaoPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens
                .stream()
                .map( item -> InformacaoItemPedidoDTO.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()).collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody AtualizaStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoservice.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }
}
