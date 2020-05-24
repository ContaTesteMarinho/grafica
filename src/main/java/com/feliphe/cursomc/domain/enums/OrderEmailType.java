package com.feliphe.cursomc.domain.enums;

public enum OrderEmailType {

	NEW_ORDER("Novo Pedido", "email/confirmacaoPedido"),
	STATUS_CHANGE("Mudança da Situação", "email/statusPedidoModificado");
	
	private String description;
	private String template;
	
	OrderEmailType(String orderEmailType, String template) {
		this.description = orderEmailType;
		this.template = template;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
}
