package com.feliphe.cursomc.desconto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feliphe.cursomc.desconto.domain.Desconto;

public interface DescontoRepository extends JpaRepository<Desconto, Integer>, DescontoCustomRepository {

}
