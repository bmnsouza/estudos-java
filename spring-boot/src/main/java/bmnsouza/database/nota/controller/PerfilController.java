package	bmnsouza.database.nota.controller;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.nota.service.PerfilService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/perfil")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Positive Integer id) {
		return perfilService.buscarPorId(id);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos() {
		return perfilService.buscarTodos();
	}

}