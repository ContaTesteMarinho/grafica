package com.feliphe.cursomc.produto.filter;

public class ProdutoQueryFilter {

	private String nome;

	private Integer pedido;

	private Integer[] categorias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public Integer[] getCategorias() {
		return categorias;
	}

	public void setCategorias(Integer[] categorias) {
		this.categorias = categorias;
	}

}
