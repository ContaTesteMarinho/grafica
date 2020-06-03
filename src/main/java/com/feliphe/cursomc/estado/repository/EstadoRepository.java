package com.feliphe.cursomc.estado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feliphe.cursomc.estado.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	@Transactional(readOnly=true)
	List<Estado> findAllByOrderByNome();
}
