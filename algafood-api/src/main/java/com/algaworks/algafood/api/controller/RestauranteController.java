package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.algaworks.algafood.utils.Utils;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if(restaurante.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(restaurante.get());
	}
	
	
	@PostMapping
	public ResponseEntity<?> adicionar(@Valid @RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch(EntidadeNaoEncontradaException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	
	@PutMapping(value = "{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Restaurante restaurante) {
		try {
			Optional<Restaurante> restauranteDb = restauranteRepository.findById(id);
			
			if(restauranteDb.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			restaurante = restauranteService.atualizar(restaurante, restauranteDb.get());
			return ResponseEntity.ok(restaurante);
		
		} catch(EntidadeNaoEncontradaException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		Optional<Restaurante> restauranteDb = restauranteRepository.findById(id);
		
		if(restauranteDb.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Restaurante restaurante = Utils.merge(campos, restauranteDb.get());
		
		return atualizar(id, restaurante);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
		try {
			restauranteService.excluir(id);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
