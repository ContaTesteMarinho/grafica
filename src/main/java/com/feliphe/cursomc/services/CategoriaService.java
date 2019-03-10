package com.feliphe.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.domain.Categoria;
import com.feliphe.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired /*Automaticamente estânciada pelo spring*/
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
