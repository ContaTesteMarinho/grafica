package com.feliphe.cursomc.pedido.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;

import com.feliphe.cursomc.pedido.domain.Pedido;

public class PedidoCustomRepositoryImpl implements PedidoCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Pedido> listByCliente(Integer clienteId, Pageable pageable) {

		StringBuilder hql = new StringBuilder();

		hql.append("  FROM Pedido ");
		hql.append(" WHERE cliente.id = :clienteId ");

		TypedQuery<Pedido> typedQuery = this.entityManager.createQuery(hql.toString(), Pedido.class);

		typedQuery.setParameter("clienteId", clienteId);

		// Paginação
		typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		typedQuery.setMaxResults(pageable.getPageSize());

		return typedQuery.getResultList();
	}

}
