package	bmnsouza.database.nota.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.nota.entity.BloqueioResgate;
import bmnsouza.database.nota.service.BloqueioResgateService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/bloqueioResgate")
public class BloqueioResgateController {

	@Autowired
	private BloqueioResgateService bloqueioResgateService;

	@GetMapping("buscar")
	public ResponseEntity<EntidadeResult> buscar() {
		return bloqueioResgateService.buscar();
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid BloqueioResgate bloqueioResgate) {
		return bloqueioResgateService.cadastrar(bloqueioResgate);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid BloqueioResgate bloqueioResgate) {
		return bloqueioResgateService.atualizar(bloqueioResgate);
	}

	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam LocalDate dataBloqueio) {
		return bloqueioResgateService.remover(dataBloqueio);
	}

}