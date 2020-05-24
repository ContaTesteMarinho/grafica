package com.feliphe.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.feliphe.cursomc.domain.Desconto;

public class DescontoDTO implements Serializable {

	private static final long serialVersionUID = 8464915628278142902L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	private Double percentual;

	private Integer quantidadePedidos;

	public DescontoDTO() {
	}

	public DescontoDTO(Desconto obj) {

		this.id = obj.getId();
		this.percentual = obj.getPercentual();
		this.quantidadePedidos = obj.getQuantidadePedidos();
	}

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
