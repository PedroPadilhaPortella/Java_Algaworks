package com.algaworks.voos.models;

public class Voo {
	
	private String origem;
	private String destino;
	private Double preco;
	
	public Voo(String origem, String destino, Double preco) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.preco = preco;
	}
	
	
	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}