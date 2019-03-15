package com.feliphe.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feliphe.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
