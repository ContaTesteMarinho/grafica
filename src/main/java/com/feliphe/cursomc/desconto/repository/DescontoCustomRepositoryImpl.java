package com.feliphe.cursomc.desconto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;

import com.feliphe.cursomc.desconto.domain.Desconto;

public class DescontoCustomRepositoryImpl implements DescontoCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Desconto> list(Pageable pageable) {

		StringBuilder hql = new StringBuilder();

		hql.append("       FROM Desconto ");
		hql.append("      WHERE 1 = 1 ");

		TypedQuery<Desconto> typedQuery = this.entityManager.createQuery(hql.toString(), Desconto.class);

		// Paginação
		typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		typedQuery.setMaxResults(pageable.getPageSize());

		return typedQuery.getResultList();
	}

}
