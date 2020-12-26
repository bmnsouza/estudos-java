package bmnsouza.database.fazendario.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

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

import bmnsouza.database.fazendario.entity.Receita;
import bmnsouza.database.fazendario.service.ReceitaService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@GetMapping("encontrarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return receitaService.buscarTodos(pagina);
	}

	@GetMapping("encontrarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(999999) Integer cdReceita) {
		return receitaService.buscarPorId(cdReceita);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid Receita receita) {
		return receitaService.cadastrar(receita);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid Receita receita) {
		return receitaService.atualizar(receita);
	}

	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(999999) Integer cdReceita) {
		return receitaService.remover(cdReceita);
	}

}