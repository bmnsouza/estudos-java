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

import bmnsouza.database.fazendario.entity.PercentualCalculoReceita;
import bmnsouza.database.fazendario.entity.dto.percentualCalculoReceita.PercentualCalculoReceitaCadastrarDTO;
import bmnsouza.database.fazendario.service.PercentualCalculoReceitaService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/percentualCalculoReceita")
public class PercentualCalculoReceitaController {

	@Autowired
	private PercentualCalculoReceitaService percentualCalculoReceitaService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return percentualCalculoReceitaService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(999999) Integer cdReceita, @RequestParam @Min(1) @Max(99999) Integer nrSequencial) {
		return percentualCalculoReceitaService.buscarPorId(cdReceita, nrSequencial);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid PercentualCalculoReceitaCadastrarDTO percentualCalculoReceitaCadastrarDTO) {
		return percentualCalculoReceitaService.cadastrar(percentualCalculoReceitaCadastrarDTO);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid PercentualCalculoReceita percentualCalculoReceita) {
		return percentualCalculoReceitaService.atualizar(percentualCalculoReceita);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(999999) @NotNull Integer cdReceita, @RequestParam @Min(1) @Max(99999) @NotNull Integer nrSequencial) {
		return percentualCalculoReceitaService.remover(cdReceita, nrSequencial);
	}

}