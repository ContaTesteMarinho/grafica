package com.feliphe.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.domain.Estado;
import com.feliphe.cursomc.repositories.EstadoRepository;
import com.feliphe.cursomc.services.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo; 
	
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
	
	public Estado findById(Integer id) {
		return repo.findById(id).get();
	}
}