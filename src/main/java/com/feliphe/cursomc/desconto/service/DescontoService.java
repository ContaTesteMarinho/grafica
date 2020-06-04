package com.feliphe.cursomc.desconto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.desconto.domain.Desconto;
import com.feliphe.cursomc.desconto.dto.DescontoDTO;
import com.feliphe.cursomc.desconto.repository.DescontoRepository;
import com.feliphe.cursomc.exception.DataIntegrityException;
import com.feliphe.cursomc.exception.ObjectNotFoundException;

@Service
public class DescontoService {

	@Autowired
	private DescontoRepository descontoRepository;

	public Desconto find(Integer id) {

		Optional<Desconto> obj = descontoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id " + id + ", Tipo: " + Desconto.class.getName()));
	}

	public List<Desconto> list(Pageable pageable) {
		return descontoRepository.list(pageable);
	}

	public Desconto insert(DescontoDTO dto) {

		Desconto entity = new Desconto();

		entity.setId(null);
		entity.setPercentual(dto.getPercentual());
		entity.setQuantidadePedidos(dto.getQuantidadePedidos());

		return descontoRepository.save(entity);
	}

	public void delete(Integer id) {

		find(id);
		try {
			descontoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma desconto que tenha um ou mais pedidos.");
		}
	}

}
