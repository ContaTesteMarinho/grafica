package com.feliphe.cursomc.desconto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feliphe.cursomc.pedido.domain.Pedido;

@Entity
public class Desconto implements Serializable {

	private static final long serialVersionUID = 4539611990813503977L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double percentual;
	private Integer quantidadePedidos;

	@JsonIgnore
	@OneToMany(mappedBy = "desconto")
	private List<Pedido> pedidos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	public Integer getQuantidadePedidos() {
		return quantidadePedidos;
	}

	public void setQuantidadePedidos(Integer quantidadePedidos) {
		this.quantidadePedidos = quantidadePedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
