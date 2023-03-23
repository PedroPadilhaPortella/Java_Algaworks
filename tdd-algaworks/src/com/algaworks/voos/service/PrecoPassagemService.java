package com.algaworks.voos.service;

import com.algaworks.voos.models.Passageiro;
import com.algaworks.voos.models.Voo;

public class PrecoPassagemService {
	
	public double calcular(Passageiro passageiro, Voo voo) {		
		return passageiro.getTipo().getCalculadora().calcular(voo);
	}
}