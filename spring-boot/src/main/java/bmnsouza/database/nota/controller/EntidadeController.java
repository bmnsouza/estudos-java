package	bmnsouza.database.nota.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;
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
import bmnsouza.database.nota.entity.Entidade;
import bmnsouza.database.nota.entity.dto.entidade.EntidadeAtualizarSenhaDTO;
import bmnsouza.database.nota.entity.dto.entidade.EntidadeCadastrarDTO;
import bmnsouza.database.nota.service.EntidadeService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/entidade")
public class EntidadeController {

	@Autowired
	private EntidadeService entidadeService;

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Positive Integer id) {
		return entidadeService.buscarPorId(id);
	}

	@GetMapping("buscarPorCnpj")
	public ResponseEntity<EntidadeResult> buscarPorCnpj(@RequestParam @CNPJ String cnpj) {
		return entidadeService.buscarPorCnpj(cnpj);
	}

	@GetMapping("buscarPorRazao")
	public ResponseEntity<EntidadeResult> buscarPorRazao(@RequestParam @NotEmpty String razao, @RequestParam @PositiveOrZero int pagina) {
		return entidadeService.buscarPorRazao(razao, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return entidadeService.buscarTodos(pagina);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid EntidadeCadastrarDTO entidadeCadastroDTO) throws ServiceException {
		return entidadeService.cadastrar(entidadeCadastroDTO);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid Entidade entidade) {
		return entidadeService.atualizar(entidade);
	}

	@PutMapping("atualizarSenha")
	public ResponseEntity<EntidadeResult> atualizarSenha(@RequestBody @Valid EntidadeAtualizarSenhaDTO entidadeAtualizarSenhaDTO) throws ServiceException {
		return entidadeService.atualizarSenha(entidadeAtualizarSenhaDTO);
	}

}