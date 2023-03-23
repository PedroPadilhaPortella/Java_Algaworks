package com.algaworks.voos.service;

import com.algaworks.voos.models.Voo;

public class PrecoPassagemPlatinum implements CalculadoraPrecoPassagem {

	@Override
	public double calcular(Voo voo) {
		if(voo.getPreco() > 500) {
			return voo.getPreco() * 0.80;
		}
		return voo.getPreco() * 0.85;
	}

}
