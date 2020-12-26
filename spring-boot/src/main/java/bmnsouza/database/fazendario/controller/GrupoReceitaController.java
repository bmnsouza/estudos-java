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

import bmnsouza.database.fazendario.entity.GrupoReceita;
import bmnsouza.database.fazendario.entity.dto.grupoReceita.GrupoReceitaDTO;
import bmnsouza.database.fazendario.service.GrupoReceitaService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/grupoReceita")
public class GrupoReceitaController {

	@Autowired
	private GrupoReceitaService grupoReceitaService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina){
		return grupoReceitaService.buscarTodos(pagina);	
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Max(99) @Min(1) @NotNull Integer nrGrupoReceita){
		return grupoReceitaService.buscarPorId(nrGrupoReceita);	
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid GrupoReceitaDTO grupoReceitaDTO){
		return grupoReceitaService.cadastrar(grupoReceitaDTO);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid GrupoReceita grupoReceita){
		return grupoReceitaService.atualizar(grupoReceita);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Max(99) @Min(1) @NotNull Integer nrGrupoReceita){
		return grupoReceitaService.remover(nrGrupoReceita);
	}
	
}
