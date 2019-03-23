package com.feliphe.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.domain.Pedido;
import com.feliphe.cursomc.repositories.PedidoRepository;
import com.feliphe.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
