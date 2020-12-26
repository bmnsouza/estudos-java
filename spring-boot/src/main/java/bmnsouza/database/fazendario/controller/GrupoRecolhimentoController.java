package bmnsouza.database.fazendario.controller;

import javax.validation.Valid;
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

import bmnsouza.database.fazendario.entity.GrupoRecolhimento;
import bmnsouza.database.fazendario.entity.dto.grupoRecolhimento.GrupoRecolhimentoDTO;
import bmnsouza.database.fazendario.service.GrupoRecolhimentoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/grupoRecolhimento")
public class GrupoRecolhimentoController {
	
	@Autowired
	private GrupoRecolhimentoService grupoRecolhimentoService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return grupoRecolhimentoService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) Integer nrGrupoRecolhimento) {
		return grupoRecolhimentoService.buscarPorId(nrGrupoRecolhimento);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid GrupoRecolhimentoDTO grupoRecolhimento ) {
		return grupoRecolhimentoService.cadastrar(grupoRecolhimento);
	}
		
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid GrupoRecolhimento grupoRecolhimento){
		return grupoRecolhimentoService.atualizar(grupoRecolhimento);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) Integer nrGrupoRecolhimento) {
		return grupoRecolhimentoService.remover(nrGrupoRecolhimento);
	}
	
}
