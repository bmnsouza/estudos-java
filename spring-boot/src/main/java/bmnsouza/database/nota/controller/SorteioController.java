package	bmnsouza.database.nota.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.nota.entity.Sorteio;
import bmnsouza.database.nota.service.SorteioService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/sorteio")
public class SorteioController {

	@Autowired
	private SorteioService sorteioService;

	@GetMapping("buscarPorCodigo")
	public ResponseEntity<EntidadeResult> buscarPorCodigo(@RequestParam @Positive Integer codSorteio) {
		return sorteioService.buscarPorCodigo(codSorteio);
	}

	@GetMapping("buscarExpirados")
	public ResponseEntity<EntidadeResult> buscarExpirados(@RequestParam @PositiveOrZero int pagina) {
		return sorteioService.buscarExpirados(pagina);
	}

	@GetMapping("buscarNaoExpirados")
	public ResponseEntity<EntidadeResult> buscarNaoExpirados() {
		return sorteioService.buscarNaoExpirados();
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return sorteioService.buscarTodos(pagina);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid Sorteio sorteio) {
		return sorteioService.cadastrar(sorteio);
	}

}