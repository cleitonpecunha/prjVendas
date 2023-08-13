package io.github.cleitonpecunha.domain.repository;

import io.github.cleitonpecunha.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    //sql não nativo
    //@Query(value = " select c from Cliente c where c.nome like :nome ")
    //sql nativo
    @Query(value = " select * from cliente c where c.nome like '%:nome%' ",nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    List<Cliente> findByNomeLike(String nome);

    //List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    //Cliente findByOneNome(String nome);

    //@Query("delete from Cliente c where c.nome =:nome")
    //@Modifying // para delete e update
    //void deleteByNome(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

    boolean existsByNome(String nome);

    boolean existsByCpf(String cpf);

}
