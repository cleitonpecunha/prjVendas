package io.github.cleitonpecunha.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "{campo.pedidoid.itempedido.obrigatorio}")
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    @NotEmpty(message = "{campo.produtoid.itempedido.obrigatorio}")
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotEmpty(message = "{campo.quantidade.itempedido.obrigatorio}")
    @Column
    private Integer quantidade;

}
