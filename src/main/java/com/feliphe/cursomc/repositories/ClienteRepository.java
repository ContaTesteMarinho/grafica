package com.feliphe.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feliphe.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
