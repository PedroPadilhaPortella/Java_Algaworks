package com.algaworks.pedido;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.algaworks.pedidos.desconto.CalculadoraDescontoPrimeiraFaixa;
import com.algaworks.pedidos.desconto.CalculadoraDescontoSegundaFaixa;
import com.algaworks.pedidos.desconto.CalculadoraDescontoTerceiraFaixa;
import com.algaworks.pedidos.desconto.CalculadoraFaixaDesconto;
import com.algaworks.pedidos.desconto.SemDesconto;
import com.algaworks.pedidos.exceptions.ValorFornecidoInvalidoException;
import com.algaworks.pedidos.ItemPedido;
import com.algaworks.pedidos.Pedido;
import com.algaworks.pedidos.ResumoPedido;

public class PedidoTest {
	
	private Pedido pedido;
	//private PedidoBuilder pedidoBuilder;
	
	@Before
	public void setup() {
		CalculadoraFaixaDesconto calculadoraFaixaDesconto = new CalculadoraDescontoTerceiraFaixa(
			new CalculadoraDescontoSegundaFaixa(
				new CalculadoraDescontoPrimeiraFaixa(
					new SemDesconto(null))));
		
		pedido = new Pedido(calculadoraFaixaDesconto);
		//pedidoBuilder = new PedidoBuilder();
	}
	
	@Test
	public void deveCalcularValorTotalEDescontoParaPedidoVazio() throws Exception {
		ResumoPedido resumoPedido = pedido.getResumoPedido();
		assertEquals(0.0, resumoPedido.getValorTotal(), 0);
		assertEquals(0.0, resumoPedido.getDesconto(), 0);
	}
	
	@Test
	public void deveCalcularResumoParaUmItemSemDesconto() throws Exception {
		ItemPedido itemPedido = new ItemPedido("Sabonete", 3.00, 10);
		pedido.adicionarItem(itemPedido);
		ResumoPedido resumoPedido = pedido.getResumoPedido();
		assertEquals(30.0, resumoPedido.getValorTotal(), 0);
	}
	
	@Test
	public void deveCalcularResumoParaDoisItensSemDesconto() throws Exception {
		//pedidoBuilder.comItem(10, 2).comItem(5, 100);
		//pedidoBuilder.construir().getResumoPedido();
		pedido.adicionarItem(new ItemPedido("Sabonete", 3.00, 5));
		pedido.adicionarItem(new ItemPedido("Xampu", 9.00, 2));
		ResumoPedido resumoPedido = pedido.getResumoPedido();
		assertEquals(33.0, resumoPedido.getValorTotal(), 0);
	}
		
	@Test
	public void deveAplicarDescontoDe4PorcentoParaPedidosAcimaDe300Reais() throws Exception {
		pedido.adicionarItem(new ItemPedido("Monster", 10.0, 40));
		ResumoPedido resumoPedido = pedido.getResumoPedido();
		assertEquals(16.0, resumoPedido.getDesconto(), 0);
	}
	
	@Test
	public void deveAplicarDescontoDe6PorcentoParaPedidosAcimaDe800Reais() throws Exception {
		pedido.adicionarItem(new ItemPedido("Monster", 18.0, 50));
		ResumoPedido resumoPedido = pedido.getResumoPedido();
		assertEquals(54.0, resumoPedido.getDesconto(), 0);
	}
	
	@Test
	public void deveAplicarDescontoDe8PorcentoParaPedidosAcimaDe1000Reais() throws Exception {
		pedido.adicionarItem(new ItemPedido("Celular Xiaomi", 1200.0, 1));
		ResumoPedido resumoPedido = pedido.getResumoPedido();
		assertEquals(96.0, resumoPedido.getDesconto(), 0);
	}
	
	@Test(expected=ValorFornecidoInvalidoException.class)
	public void naoDeveAceitarPedidosComItemEmQuantidadesMenoresQueZero() throws Exception {
		pedido.adicionarItem(new ItemPedido("Celular Xiaomi", 1200.0, -10));
	}
	
	@Test(expected=ValorFornecidoInvalidoException.class)
	public void naoDeveAceitarPedidosComItemComValorUnitarioMenoresQueZero() throws Exception {
		pedido.adicionarItem(new ItemPedido("Celular Xiaomi", -200.0, 10));
	}
}