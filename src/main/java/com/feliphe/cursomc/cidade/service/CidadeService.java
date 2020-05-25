package com.feliphe.cursomc.cidade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.cidade.domain.Cidade;
import com.feliphe.cursomc.cidade.repository.CidadeRepository;
import com.feliphe.cursomc.estado.domain.Estado;

@Service
public class CidadeService {

	@Autowired
	public CidadeRepository cidadeRepo;
	
	public List<Cidade> findAllByEstado(Estado estado) {
		return cidadeRepo.findByEstado(estado);
	}
}
