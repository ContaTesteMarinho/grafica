package com.feliphe.cursomc.dto;

import java.io.Serializable;
import java.util.Date;

import com.feliphe.cursomc.domain.Pedido;
import com.feliphe.cursomc.domain.enums.Status;

public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = -3678202937943980050L;

	private Integer id;
	private Integer status;
	private Date instante;

	public PedidoDTO() {
	}

	public PedidoDTO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.status = pedido.getStatus().getCod();
		this.instante = pedido.getInstante();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

}
