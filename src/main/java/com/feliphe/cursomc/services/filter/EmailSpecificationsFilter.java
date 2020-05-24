package com.feliphe.cursomc.services.filter;

import com.feliphe.cursomc.domain.enums.OrderEmailType;

public class EmailSpecificationsFilter {

	private OrderEmailType orderEmailType;
	private String template;

	public OrderEmailType getOrderEmailType() {
		return orderEmailType;
	}

	public void setOrderEmailType(OrderEmailType orderEmailType) {
		this.orderEmailType = orderEmailType;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	
}
