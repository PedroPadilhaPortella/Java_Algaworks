package com.algaworks.voos.models;

import com.algaworks.voos.service.CalculadoraPrecoPassagem;
import com.algaworks.voos.service.PrecoPassagemGold;
import com.algaworks.voos.service.PrecoPassagemPlatinum;
import com.algaworks.voos.service.PrecoPassagemSilver;

public enum TipoPassageiro {
	
	PLATINUM(new PrecoPassagemPlatinum()),
	GOLD(new PrecoPassagemGold()),
	SILVER(new PrecoPassagemSilver());
	
	CalculadoraPrecoPassagem calculadoraPrecoPassagem;
	
	TipoPassageiro(CalculadoraPrecoPassagem calculadoraPrecoPassagem) {
		this.calculadoraPrecoPassagem = calculadoraPrecoPassagem;
	}
	
	public CalculadoraPrecoPassagem getCalculadora() {
		return calculadoraPrecoPassagem;
	}
}
