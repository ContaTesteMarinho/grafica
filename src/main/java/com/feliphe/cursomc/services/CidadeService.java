package com.feliphe.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.domain.Cidade;
import com.feliphe.cursomc.domain.Estado;
import com.feliphe.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	public CidadeRepository cidadeRepo;
	
	public List<Cidade> findAllByEstado(Estado estado) {
		return cidadeRepo.findByEstado(estado);
	}
}
