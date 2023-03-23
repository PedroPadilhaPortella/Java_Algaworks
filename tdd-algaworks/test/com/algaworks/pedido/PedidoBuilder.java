package com.algaworks.pedido;

import com.algaworks.pedidos.desconto.CalculadoraDescontoPrimeiraFaixa;
import com.algaworks.pedidos.desconto.CalculadoraDescontoSegundaFaixa;
import com.algaworks.pedidos.desconto.CalculadoraDescontoTerceiraFaixa;
import com.algaworks.pedidos.desconto.CalculadoraFaixaDesconto;
import com.algaworks.pedidos.desconto.SemDesconto;
import com.algaworks.pedidos.ItemPedido;
import com.algaworks.pedidos.Pedido;

public class PedidoBuilder {

	private Pedido instance;
	
	PedidoBuilder() {
		CalculadoraFaixaDesconto calculadoraFaixaDesconto = new CalculadoraDescontoTerceiraFaixa(
			new CalculadoraDescontoSegundaFaixa(
				new CalculadoraDescontoPrimeiraFaixa(
					new SemDesconto(null))));
			
		this.instance = new Pedido(calculadoraFaixaDesconto);
	}
	
	public PedidoBuilder comItem(double valorUnitario, int quantidade) {
		instance.adicionarItem(new ItemPedido("Fake Pedido", valorUnitario, quantidade));
		return this;
	}
	
	public Pedido construir() {
		return instance;
	}
}
