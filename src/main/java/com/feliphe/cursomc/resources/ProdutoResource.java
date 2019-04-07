package com.feliphe.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feliphe.cursomc.domain.Produto;
import com.feliphe.cursomc.dto.ProdutoDTO;
import com.feliphe.cursomc.resources.utils.URL;
import com.feliphe.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> buscar(@PathVariable Integer id) {
		
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecode = URL.decodeParam(nome);
		List<Integer> ids =  URL.decodeIntList(categorias);
		Page<Produto> produtos = service.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtosDTO = produtos.map(produto -> new ProdutoDTO(produto));
		
		return ResponseEntity.ok().body(produtosDTO);
	}
}