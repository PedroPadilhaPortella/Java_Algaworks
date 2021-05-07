package com.gft.algafood.domain.repository;

import java.util.List;

import com.gft.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listarTodos();
	Restaurante buscarPorId(Long id);
	Restaurante adicionar(Restaurante restaurante);
	void remover(Restaurante restaurante); 
}
