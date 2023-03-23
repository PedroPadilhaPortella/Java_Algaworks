package com.algaworks.pedidos;

public class ItemPedido {
	
	private String descricao;
	private Double valorUnitario;
	private Integer quantidade;

	public ItemPedido(String descricao, Double valorUnitario, Integer quantidade) {
		this.descricao = descricao;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}