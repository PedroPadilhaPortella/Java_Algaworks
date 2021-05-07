package com.gft.algafood.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.gft.algafood.AlgafoodApiApplication;
import com.gft.algafood.domain.model.Cozinha;
import com.gft.algafood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhaMain {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
		Cozinha cozinha = new Cozinha();
		cozinha.setId(2L);
		cozinha.setNome("Alemã");
		
		cozinha = cozinhas.adicionar(cozinha);
		
		System.out.println(cozinha.getId() + ", " + cozinha.getNome());
	}
}
