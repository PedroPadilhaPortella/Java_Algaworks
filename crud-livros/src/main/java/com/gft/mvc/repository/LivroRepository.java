package com.gft.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.mvc.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	public List<Livro> findByTituloContaining(String titulo);
}