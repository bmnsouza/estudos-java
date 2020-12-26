package	bmnsouza.database.nota.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
import bmnsouza.database.nota.entity.Usuario;
import bmnsouza.database.nota.service.UsuarioService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf) throws ServiceException {
		return usuarioService.buscarPorCpf(cpf);
	}

	@GetMapping("buscarPorNome")
	public ResponseEntity<EntidadeResult> buscarPorNome(@RequestParam @NotEmpty String nome, @RequestParam @PositiveOrZero int pagina) {
		return usuarioService.buscarPorNome(nome, pagina);
	}

	@GetMapping("buscarPorPerfil")
	public ResponseEntity<EntidadeResult> buscarPorPerfil(@RequestParam @PositiveOrZero int codPerfil, @RequestParam @PositiveOrZero int pagina) {
		return usuarioService.buscarPorPerfil(codPerfil, pagina);
	}

	@GetMapping("buscarPorStatus")
	public ResponseEntity<EntidadeResult> buscarPorStatus(@RequestParam @PositiveOrZero int status, @RequestParam @PositiveOrZero int pagina) {
		return usuarioService.buscarPorStatus(status, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return usuarioService.buscarTodos(pagina);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid Usuario usuario) {
		return usuarioService.cadastrar(usuario);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid Usuario usuario) {
		return usuarioService.atualizar(usuario);
	}

}