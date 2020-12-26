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
import bmnsouza.database.nota.entity.Consumidor;
import bmnsouza.database.nota.entity.dto.consumidor.ConsumidorAtualizarSenhaDTO;
import bmnsouza.database.nota.entity.dto.consumidor.ConsumidorCadastrarDTO;
import bmnsouza.database.nota.service.ConsumidorService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/consumidor")
public class ConsumidorController {

	@Autowired
	private ConsumidorService consumidorService;

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Positive Integer id) {
		return consumidorService.buscarPorId(id);
	}

	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf) {
		return consumidorService.buscarPorCpf(cpf);
	}

	@GetMapping("buscarPorNome")
	public ResponseEntity<EntidadeResult> buscarPorNome(@RequestParam @NotEmpty String nome, @RequestParam @PositiveOrZero int pagina) {
		return consumidorService.buscarPorNome(nome, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return consumidorService.buscarTodos(pagina);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid ConsumidorCadastrarDTO consumidorCadastroDTO) throws ServiceException {
		return consumidorService.cadastrar(consumidorCadastroDTO);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid Consumidor consumidor) {
		return consumidorService.atualizar(consumidor);
	}

	@PutMapping("atualizarSenha")
	public ResponseEntity<EntidadeResult> atualizarSenha(@RequestBody @Valid ConsumidorAtualizarSenhaDTO consumidorAtualizarSenhaDTO) throws ServiceException {
		return consumidorService.atualizarSenha(consumidorAtualizarSenhaDTO);
	}

}