package io.github.cleitonpecunha.rest.controller;

import io.github.cleitonpecunha.domain.entity.Produto;
import io.github.cleitonpecunha.exception.RegraNegocioException;
import io.github.cleitonpecunha.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    /*// instanciando a classe de PRODUTOS (repository)
    private Produtos produtos;

    // Construtor de PRODUTOS
    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }*/

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /*// Salvar os dados (Insert)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return produtos.save(produto);
    }*/

    // Salvar os dados (Insert)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid Produto produto){

        try {
            Produto salvarProduto = produtoService.salvar(produto);
            return salvarProduto.getId();
        } catch (RegraNegocioException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    /*// Salvar os dados (Update)
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Produto produto) {
        produtos.findById(id)
                .map( produtoExistente -> {
                    produto.setId( produtoExistente.getId());
                    produtos.save(produto);
                    return produtoExistente;
                })
                .orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado."));
    }

    // Excluir os dados (Delete)
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id) {
        produtos.findById(id)
                .map( produto -> {
                    produtos.delete(produto);
                    return produto;
                })
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado."));
    }

    // Buscar o registro por ID
    @GetMapping("{id}")
    public Produto getProdutoById( @PathVariable Integer id) {
        return produtos.findById(id)
                       .orElseThrow( () ->
                               new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado."));
    }

    // Listar os registros pela condição (filtro)
    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtos.findAll(example);
    }*/

}
