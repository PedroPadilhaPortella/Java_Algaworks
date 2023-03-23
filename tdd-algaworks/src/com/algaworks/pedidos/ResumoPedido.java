package com.algaworks.pedidos;

public class ResumoPedido {

	private Double valorTotal;
	private Double desconto;
	
	public ResumoPedido(Double valorTotal, Double desconto) {
		super();
		this.valorTotal = valorTotal;
		this.desconto = desconto;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Double getDesconto() {
		return desconto;
	}
	
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	@Override
	public String toString() {
		return "ResumoPedido: ValorTotal: R$" + valorTotal + ", Desconto Aplicado: R$" + desconto;
	}
	
	
}