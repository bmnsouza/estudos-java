package	bmnsouza.database.nota.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.exception.ServiceException;
import bmnsouza.database.nota.entity.Procon;
import bmnsouza.database.nota.entity.dto.procon.ProconAtualizarSenhaDTO;
import bmnsouza.database.nota.entity.dto.procon.ProconCadastrarDTO;
import bmnsouza.database.nota.service.ProconService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/procon")
public class ProconController {

	@Autowired
	private ProconService proconService;

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Positive Integer id) {
		return proconService.buscarPorId(id);
	}

	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf) {
		return proconService.buscarPorCpf(cpf);
	}

	@GetMapping("buscarPorNome")
	public ResponseEntity<EntidadeResult> buscarPorNome(@RequestParam @NotEmpty String nome, @RequestParam @PositiveOrZero int pagina) {
		return proconService.buscarPorNome(nome, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return proconService.buscarTodos(pagina);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid ProconCadastrarDTO proconCadastrarDTO) throws ServiceException {
		return proconService.cadastrar(proconCadastrarDTO);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid Procon procon) {
		return proconService.atualizar(procon);
	}

	@PutMapping("atualizarSenha")
	public ResponseEntity<EntidadeResult> atualizarSenha(@RequestBody @Valid ProconAtualizarSenhaDTO proconAtualizarSenhaDTO) throws ServiceException {
		return proconService.atualizarSenha(proconAtualizarSenhaDTO);
	}

}