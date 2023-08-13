package io.github.cleitonpecunha.service.impl;

import io.github.cleitonpecunha.domain.entity.Produto;
import io.github.cleitonpecunha.domain.repository.Produtos;
import io.github.cleitonpecunha.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ProdutoServiceImpl implements ProdutoService {

    private final Produtos produtoRepository;

    @Override
    @Transactional
    public Produto salvar(Produto produto){

        Produto novoProduto = new Produto();
        novoProduto.setDescricao(produto.getDescricao());
        novoProduto.setPreco(produto.getPreco());
        produtoRepository.save(novoProduto);
        return novoProduto;

    }

}
