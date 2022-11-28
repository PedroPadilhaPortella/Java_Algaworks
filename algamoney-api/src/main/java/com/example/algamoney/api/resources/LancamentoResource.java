package com.example.algamoney.api.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.ResourceCreateEvent;
import com.example.algamoney.api.exceptionHandler.AlgamoneyApiExceptionHandler.Erro;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import com.example.algamoney.api.service.LancamentoService;
import com.example.algamoney.api.service.exception.PessoaInativaOuInexistenteException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	//@PreAuthorize("hasAuhtority('ROLE_PESQUISAR_CATEGORIA')")
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasAuhtority('ROLE_PESQUISAR_CATEGORIA')")
	public ResponseEntity<Lancamento> getById(@PathVariable Long id) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	//@PreAuthorize("hasAuhtority('ROLE_CADASTRAR_CATEGORIA')")
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		lancamento = lancamentoService.salvar(lancamento);		
		publisher.publishEvent(new ResourceCreateEvent(this, response, lancamento.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}
	
	
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasAuhtority('ROLE_CADASTRAR_CATEGORIA')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		lancamentoRepository.deleteById(id);
	}
	
	@ExceptionHandler({ PessoaInativaOuInexistenteException.class })
	public ResponseEntity<Object> handlePessoaInativaOuInexistenteException(PessoaInativaOuInexistenteException ex) {
		String messageUser = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String messageDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDev));
		return ResponseEntity.badRequest().body(erros);
	}
}
