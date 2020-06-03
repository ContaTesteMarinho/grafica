package com.feliphe.cursomc.produto.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.feliphe.cursomc.produto.domain.Produto;
import com.feliphe.cursomc.produto.filter.ProdutoQueryFilter;

public interface ProdutoCustomRepository {
	
	List<Produto> list(ProdutoQueryFilter filter, Pageable pageable);
}
