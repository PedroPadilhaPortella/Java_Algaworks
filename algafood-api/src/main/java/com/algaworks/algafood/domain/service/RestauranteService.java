package com.algaworks.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> 
			new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não encontrada", cozinhaId))
		);
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	

	public Restaurante atualizar(Restaurante restaurante, Restaurante restauranteDb) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> 
			new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não encontrada", cozinhaId))
		);
		
		restaurante.setCozinha(cozinha);
		BeanUtils.copyProperties(restaurante, restauranteDb, "id");
		
		return restauranteRepository.save(restauranteDb);
	}
	
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);
			
		} catch(DataIntegrityViolationException exception) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante de id %d não pode ser removida pois está sendo usada "
					+ "como chave estrangeira em outras tabelas", id));
			
		} catch(EmptyResultDataAccessException exception) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante de id %d não encontrada", id));
			
		} catch(IllegalArgumentException exception) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante de id %d não encontrada", id));
		}
	}
}
