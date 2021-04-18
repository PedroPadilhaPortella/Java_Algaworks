package com.gft.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O Título é obrigatória")
	@Size(max = 60, message = "O Título não pode conter mais de 60 caracteres")
	private String titulo;
	
	@NotBlank(message = "O Nome do Autor é obrigatório")
	@Size(max = 60, message = "O Nome do Autor não pode conter mais de 60 caracteres")
	private String autor;
	
	@NotBlank(message = "A Editora é obrigatória")
	@Size(max = 60, message = "A Editora não pode conter mais de 60 caracteres")
	private String editora;
	
	@NotNull(message = "A Quantidade de Páginas é Obrigatória")
	private int quantidadePaginas;
	
	@NotNull(message = "A Quantidade de Exemplares Disponíveis é Obrigatória")
	private int quantidadeExemplares;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(int quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}

	public int getQuantidadeExemplares() {
		return quantidadeExemplares;
	}

	public void setQuantidadeExemplares(int quantidadeExemplares) {
		this.quantidadeExemplares = quantidadeExemplares;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
