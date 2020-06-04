package com.feliphe.cursomc.pedido.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.feliphe.cursomc.pedido.domain.Pedido;

public interface PedidoCustomRepository {
	
	List<Pedido> listByCliente(Integer clienteId, Pageable pageable);

}
