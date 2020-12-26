package bmnsouza.database.fazendario.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

import bmnsouza.database.fazendario.entity.PercentualRepasseReceita;
import bmnsouza.database.fazendario.service.PercentualRepasseReceitaService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/percentualRepasseReceita")
public class PercentualRepasseReceitaController {
		
	@Autowired
	private PercentualRepasseReceitaService percentualRepasseReceitaService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return percentualRepasseReceitaService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(999999) @NotNull Integer cdReceita,
			@RequestParam @Min(1) @Max(9999) @NotNull Integer cdEntidade,
			@RequestParam @Min(1) @Max(2) @NotNull Integer tpProducao) {
		return percentualRepasseReceitaService.buscarPorId(cdReceita, cdEntidade, tpProducao);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid PercentualRepasseReceita percentualRepasseReceita) {
		return percentualRepasseReceitaService.cadastrar(percentualRepasseReceita);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid PercentualRepasseReceita percentualRepasseReceita) {
		return percentualRepasseReceitaService.atualizar(percentualRepasseReceita);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(999999) @NotNull Integer cdReceita,
			@RequestParam @Min(1) @Max(9999) @NotNull Integer cdEntidade,
			@RequestParam @Min(1) @Max(2) @NotNull Integer tpProducao) {
		return percentualRepasseReceitaService.remover(cdReceita, cdEntidade, tpProducao);
	}
	
}