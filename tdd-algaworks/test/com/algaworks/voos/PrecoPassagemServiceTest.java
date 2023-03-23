package com.algaworks.voos;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.algaworks.voos.models.Passageiro;
import com.algaworks.voos.models.TipoPassageiro;
import com.algaworks.voos.models.Voo;
import com.algaworks.voos.service.PrecoPassagemService;

public class PrecoPassagemServiceTest {
	
	@Test
	public void deveCalcularPrecoDaPassagemParaPassageiroPlatinumComDesconto20() throws Exception {
		Passageiro passageiro = new Passageiro("Pedro", TipoPassageiro.PLATINUM);
		Voo voo = new Voo("São Paulo", "Curitiba", 1000.0);
		
		PrecoPassagemService precoPassagemService = new PrecoPassagemService();
		double valor = precoPassagemService.calcular(passageiro, voo);
		assertEquals(valor, 800.0, 0);
	}
	
	@Test
	public void deveCalcularPrecoDaPassagemParaPassageiroPlatinumComDescontoDe15() throws Exception {
		Passageiro passageiro = new Passageiro("Pedro", TipoPassageiro.PLATINUM);
		Voo voo = new Voo("São Paulo", "Curitiba", 100.0);
		
		PrecoPassagemService precoPassagemService = new PrecoPassagemService();
		double valor = precoPassagemService.calcular(passageiro, voo);
		assertEquals(valor, 85.0, 0);
	}
	
	@Test
	public void deveCalcularPrecoDaPassagemParaPassageiroGoldComDesconto10() throws Exception {
		Passageiro passageiro = new Passageiro("Pedro", TipoPassageiro.GOLD);
		Voo voo = new Voo("São Paulo", "Curitiba", 100.0);
		
		PrecoPassagemService precoPassagemService = new PrecoPassagemService();
		double valor = precoPassagemService.calcular(passageiro, voo);
		assertEquals(valor, 90.0, 0);
	}
	
	@Test
	public void deveCalcularPrecoDaPassagemParaPassageiroGoldComDescontoDe15() throws Exception {
		Passageiro passageiro = new Passageiro("Pedro", TipoPassageiro.GOLD);
		Voo voo = new Voo("São Paulo", "Curitiba", 600.0);
		
		PrecoPassagemService precoPassagemService = new PrecoPassagemService();
		double valor = precoPassagemService.calcular(passageiro, voo);
		assertEquals(valor, 510.0, 0);
	}
	
	
	@Test
	public void deveCalcularPrecoDaPassagemParaPassageiroSilverComDesconto6() throws Exception {
		Passageiro passageiro = new Passageiro("Pedro", TipoPassageiro.SILVER);
		Voo voo = new Voo("São Paulo", "Curitiba", 100.0);
		
		PrecoPassagemService precoPassagemService = new PrecoPassagemService();
		double valor = precoPassagemService.calcular(passageiro, voo);
		assertEquals(valor, 94.0, 0);
	}
	
	@Test
	public void deveCalcularPrecoDaPassagemParaPassageiroGoldComDescontoDe10() throws Exception {
		Passageiro passageiro = new Passageiro("Pedro", TipoPassageiro.SILVER);
		Voo voo = new Voo("São Paulo", "Curitiba", 1000.0);
		
		PrecoPassagemService precoPassagemService = new PrecoPassagemService();
		double valor = precoPassagemService.calcular(passageiro, voo);
		assertEquals(valor, 900.0, 0);
	}
}
