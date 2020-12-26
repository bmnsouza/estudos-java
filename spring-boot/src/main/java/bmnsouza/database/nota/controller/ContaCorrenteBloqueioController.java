package	bmnsouza.database.nota.controller;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.nota.entity.ContaCorrenteBloqueio;
import bmnsouza.database.nota.service.ContaCorrenteBloqueioService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/contaCorrenteBloqueio")
public class ContaCorrenteBloqueioController {

	@Autowired
	private ContaCorrenteBloqueioService contaCorrenteBloqueioService;
	
	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf, @RequestParam @PositiveOrZero int pagina) {
		return contaCorrenteBloqueioService.buscarPorCpf(cpf, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return contaCorrenteBloqueioService.buscarTodos(pagina);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid ContaCorrenteBloqueio contaCorrenteBloqueio) {
		return contaCorrenteBloqueioService.cadastrar(contaCorrenteBloqueio);
	}

}