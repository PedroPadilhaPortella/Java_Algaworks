package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		
		if(estado.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(estado.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@Valid @RequestBody Estado estado) {
		return estadoService.salvar(estado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @Valid @RequestBody Estado estado) {
		Optional<Estado> estadoDb = estadoRepository.findById(id);
		
		if(estadoDb.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(estado, estadoDb.get(), "id");
		estado = estadoService.salvar(estadoDb.get());
		return ResponseEntity.ok(estado);
		
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			estadoService.excluir(estadoId);	
			return ResponseEntity.noContent().build();
			
		} catch(EntidadeNaoEncontradaException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
			
		} catch(EntidadeEmUsoException exception) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}
	}
}
