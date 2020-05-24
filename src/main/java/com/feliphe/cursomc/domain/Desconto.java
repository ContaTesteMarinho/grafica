package com.feliphe.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Desconto implements Serializable {

	private static final long serialVersionUID = 4539611990813503977L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double percentual;
	private Integer quantidadePedidos;

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

}
