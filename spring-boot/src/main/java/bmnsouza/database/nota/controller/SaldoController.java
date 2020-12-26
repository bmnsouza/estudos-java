package	bmnsouza.database.nota.controller;

import java.math.BigDecimal;

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

import bmnsouza.database.nota.service.SaldoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/saldo")
public class SaldoController {

	@Autowired
	private SaldoService saldoService;

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Positive Integer id) {
		return saldoService.buscarPorId(id);
	}

	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf) {
		return saldoService.buscarPorCpf(cpf);
	}

	@GetMapping("buscarPorSaldoMenorQue")
	public ResponseEntity<EntidadeResult> buscarPorSaldoMenorQue(@RequestParam @PositiveOrZero BigDecimal saldo, @RequestParam @PositiveOrZero int pagina) {
		return saldoService.buscarPorSaldoMenorQue(saldo, pagina);
	}

	@GetMapping("buscarPorSaldoIgualA")
	public ResponseEntity<EntidadeResult> buscarPorSaldoIgualA(@RequestParam @PositiveOrZero BigDecimal saldo, @RequestParam @PositiveOrZero int pagina) {
		return saldoService.buscarPorSaldoIgualA(saldo, pagina);
	}

	@GetMapping("buscarPorSaldoMaiorQue")
	public ResponseEntity<EntidadeResult> buscarPorSaldoMaiorQue(@RequestParam @PositiveOrZero BigDecimal saldo, @RequestParam @PositiveOrZero int pagina) {
		return saldoService.buscarPorSaldoMaiorQue(saldo, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return saldoService.buscarTodos(pagina);
	}

}