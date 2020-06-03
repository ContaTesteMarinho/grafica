package com.feliphe.cursomc.desconto.resource;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.feliphe.cursomc.desconto.domain.Desconto;
import com.feliphe.cursomc.desconto.dto.DescontoDTO;
import com.feliphe.cursomc.desconto.service.DescontoService;

@RestController
@RequestMapping(value="/descontos")
public class DescontoResource {

	@Autowired
	DescontoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DescontoDTO> buscar(@PathVariable Integer id) {

		Desconto obj = service.find(id);
		return ResponseEntity.ok().body(new DescontoDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DescontoDTO dto) {

		Desconto entity = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
