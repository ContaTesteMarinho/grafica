package com.feliphe.cursomc.desconto.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.feliphe.cursomc.desconto.domain.Desconto;

public interface DescontoCustomRepository {
	
	List<Desconto> list(Pageable pageable);


}
