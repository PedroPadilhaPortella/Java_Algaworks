package com.gft.algafood.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.gft.algafood.AlgafoodApiApplication;
import com.gft.algafood.domain.model.Cozinha;
import com.gft.algafood.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Italiana");

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Arabe");
		
		cozinha1 = cozinhas.adicionar(cozinha1);
		cozinha2 = cozinhas.adicionar(cozinha2);
		
		System.out.println(cozinha1.getId() + ", " + cozinha1.getNome());
		System.out.println(cozinha2.getId() + ", " + cozinha2.getNome());
	}
}
