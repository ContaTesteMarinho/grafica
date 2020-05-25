package com.feliphe.cursomc.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.categoria.domain.Categoria;
import com.feliphe.cursomc.categoria.repository.CategoriaRepository;
import com.feliphe.cursomc.exception.ObjectNotFoundException;
import com.feliphe.cursomc.produto.domain.Produto;
import com.feliphe.cursomc.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Produto find(Integer id) {
		
		Optional<Produto> obj = produtoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		
		return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
