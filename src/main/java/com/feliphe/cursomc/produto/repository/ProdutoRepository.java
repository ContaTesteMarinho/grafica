package com.feliphe.cursomc.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feliphe.cursomc.produto.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoCustomRepository {

}
