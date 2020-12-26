package	bmnsouza.database.fazendario.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.exception.ServiceException;
import bmnsouza.database.fazendario.service.FazendarioService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/fazendario")
public class FazendarioController {

	@Autowired
	private FazendarioService fazendarioService;

	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf) throws ServiceException {
		return fazendarioService.buscarPorCpf(cpf);
	}

	@GetMapping("buscarPorNome")
	public ResponseEntity<EntidadeResult> buscarPorNome(@RequestParam @NotEmpty @Size(min = 3, max = 70) String nome, @RequestParam @PositiveOrZero int pagina) {
		return fazendarioService.buscarPorNome(nome, pagina);
	}

}