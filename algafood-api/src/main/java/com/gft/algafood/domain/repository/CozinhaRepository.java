package com.gft.algafood.domain.repository;

import java.util.List;

import com.gft.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listarTodas();
	Cozinha buscarPorId(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Cozinha cozinha); 
}
