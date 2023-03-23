package com.algaworks.voos.service;

import com.algaworks.voos.models.Voo;

public class PrecoPassagemSilver implements CalculadoraPrecoPassagem {

	@Override
	public double calcular(Voo voo) {
		if(voo.getPreco() > 700) {
			return voo.getPreco() * 0.90;
		}
		return voo.getPreco() * 0.94;	
	}

}
