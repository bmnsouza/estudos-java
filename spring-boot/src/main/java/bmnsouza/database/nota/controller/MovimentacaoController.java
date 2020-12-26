package	bmnsouza.database.nota.controller;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.nota.service.MovimentacaoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/movimentacao")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoService movimentacaoService;

	@GetMapping("buscarPorContaCorrente")
	public ResponseEntity<EntidadeResult> buscarPorContaCorrente(@RequestParam @Positive Integer idContaCorrente, @RequestParam @PositiveOrZero int pagina) {
		return movimentacaoService.buscarPorContaCorrente(idContaCorrente, pagina);
	}

	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf, @RequestParam @PositiveOrZero int pagina) {
		return movimentacaoService.buscarPorCpf(cpf, pagina);
	}

	@GetMapping("buscarPorSorteio")
	public ResponseEntity<EntidadeResult> buscarPorSorteio(@RequestParam @Positive Integer codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return movimentacaoService.buscarPorSorteio(codSorteio, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return movimentacaoService.buscarTodos(pagina);
	}

}