package com.feliphe.cursomc.domain.enums;

public enum Status {

	PENDENTE(1, "Pendente"), 
	EM_ANDAMENTO(2, "Em andamento"), 
	FINALIZADO(3, "Finalizado"), 
	ENTREGUE(4, "Entregue"),
	CANCELADO(5, "Cancelado");

	private int cod;
	private String descricao;

	private Status(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Status status : Status.values()) {
			if (cod.equals(status.getCod())) {
				return status;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
