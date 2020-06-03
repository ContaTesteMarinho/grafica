package com.feliphe.cursomc.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.exception.ObjectNotFoundException;
import com.feliphe.cursomc.produto.domain.Produto;
import com.feliphe.cursomc.produto.filter.ProdutoQueryFilter;
import com.feliphe.cursomc.produto.repository.ProdutoRepository;
import com.feliphe.cursomc.util.URL;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	public Produto find(Integer id) {

		Optional<Produto> obj = produtoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id " + id + ", Tipo: " + Produto.class.getName()));
	}

	public List<Produto> list(ProdutoQueryFilter filter, Pageable pageable) {

		if (filter.getNome() != null) {
			filter.setNome(URL.decodeParam(filter.getNome()));	
		}
		
		return produtoRepo.list(filter, pageable);
	}

}
