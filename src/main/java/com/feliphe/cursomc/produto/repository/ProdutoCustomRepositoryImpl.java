package com.feliphe.cursomc.produto.repository;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;

import com.feliphe.cursomc.produto.domain.Produto;
import com.feliphe.cursomc.produto.filter.ProdutoQueryFilter;

public class ProdutoCustomRepositoryImpl implements ProdutoCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Produto> list(ProdutoQueryFilter filter, Pageable pageable) {

		StringBuilder hql = new StringBuilder();

		hql.append("    SELECT DISTINCT produto ");
		hql.append("       FROM Produto produto ");
		hql.append(" INNER JOIN produto.categorias categoria ");
		hql.append("  LEFT JOIN produto.itens item ");
		hql.append("      WHERE 1 = 1 ");

		if (filter.getCategorias() != null) {
			hql.append("    AND categoria.id IN (:categorias) ");
		}

		if (filter.getNome() != null) {
			hql.append("    AND UPPER(produto.nome) LIKE UPPER(:nome) ");
		}

		if (filter.getPedido() != null) {
			hql.append("    AND item.id.pedido.id = :pedido ");
		}

		hql.append("   ORDER BY produto.nome ");

		TypedQuery<Produto> typedQuery = this.entityManager.createQuery(hql.toString(), Produto.class);

		if (filter.getCategorias() != null) {
			typedQuery.setParameter("categorias", Arrays.asList(filter.getCategorias()));
		}

		if (filter.getNome() != null) {
			typedQuery.setParameter("nome", "%" + filter.getNome() + "%");
		}

		if (filter.getPedido() != null) {
			typedQuery.setParameter("pedido", filter.getPedido());
		}

		// Paginação
		typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		typedQuery.setMaxResults(pageable.getPageSize());

		return typedQuery.getResultList();
	}

}
