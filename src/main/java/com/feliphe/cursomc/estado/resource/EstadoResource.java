package com.feliphe.cursomc.estado.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feliphe.cursomc.cidade.domain.Cidade;
import com.feliphe.cursomc.cidade.dto.CidadeDTO;
import com.feliphe.cursomc.cidade.service.CidadeService;
import com.feliphe.cursomc.estado.domain.Estado;
import com.feliphe.cursomc.estado.dto.EstadoDTO;
import com.feliphe.cursomc.estado.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> buscar() {
		
		List<Estado> obj = estadoService.findAll();
		List<EstadoDTO> estadosDto = obj.stream().map(estado ->  new EstadoDTO(estado)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estadosDto);
	}
	
	@RequestMapping(value="/{estado_id}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> buscarCidades(@PathVariable Integer estado_id) {
		
		Estado estado = estadoService.findById(estado_id);
		List<Cidade> list = cidadeService.findAllByEstado(estado);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
