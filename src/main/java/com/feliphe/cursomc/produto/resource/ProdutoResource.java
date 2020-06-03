package com.feliphe.cursomc.produto.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feliphe.cursomc.produto.domain.Produto;
import com.feliphe.cursomc.produto.dto.ProdutoDTO;
import com.feliphe.cursomc.produto.filter.ProdutoQueryFilter;
import com.feliphe.cursomc.produto.service.ProdutoService;

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
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> list(ProdutoQueryFilter filter, Pageable pageable) {

		List<Produto> entities = service.list(filter, pageable);
		
		List<ProdutoDTO> resources = new ArrayList<>();
		for (Produto entity : entities) {
			resources.add(new ProdutoDTO(entity));
		}

		return ResponseEntity.ok().body(resources);
	}
}