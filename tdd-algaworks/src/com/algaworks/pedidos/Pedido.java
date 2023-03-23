package com.algaworks.pedidos;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.pedidos.desconto.CalculadoraFaixaDesconto;
import com.algaworks.pedidos.exceptions.ValorFornecidoInvalidoException;

public class Pedido {

	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
	private CalculadoraFaixaDesconto calculadoraFaixaDesconto;
	
	public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
		this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
	}
	
	public void adicionarItem(ItemPedido item) {
		this.validarItemPedido(item);
		
		this.itensPedido.add(item);			
	}
	
	public ResumoPedido getResumoPedido() {
		double valorTotal = this.itensPedido
				.stream()
				.mapToDouble(i -> i.getValorUnitario() * i.getQuantidade())
				.sum();
		
		double desconto = this.calculadoraFaixaDesconto.desconto(valorTotal);
		
		return new ResumoPedido(valorTotal, desconto);
	}
	
	private void validarItemPedido(ItemPedido item) {
		if(item.getQuantidade() < 0 || item.getValorUnitario() < 0)
			throw new ValorFornecidoInvalidoException();
	}
}
