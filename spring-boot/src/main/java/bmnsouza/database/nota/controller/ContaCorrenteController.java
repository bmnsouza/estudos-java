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

import bmnsouza.database.nota.service.ContaCorrenteService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/contaCorrente")
public class ContaCorrenteController {

	@Autowired
	private ContaCorrenteService contaCorrenteService;
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Positive Integer id) {
		return contaCorrenteService.buscarPorId(id);
	}

	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf) {
		return contaCorrenteService.buscarPorCpf(cpf);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return contaCorrenteService.buscarTodos(pagina);
	}

}