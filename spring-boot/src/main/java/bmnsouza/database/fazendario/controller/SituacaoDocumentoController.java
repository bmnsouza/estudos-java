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

import bmnsouza.database.fazendario.entity.SituacaoDocumento;
import bmnsouza.database.fazendario.entity.dto.situacaoDocumento.SituacaoDocumentoDTO;
import bmnsouza.database.fazendario.service.SituacaoDocumentoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/situacaoDocumento")
public class SituacaoDocumentoController {
	
	@Autowired
	private SituacaoDocumentoService situacaoDocumentoService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return situacaoDocumentoService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Max(99) @Min(1) Integer nrSituacao) {
		return situacaoDocumentoService.buscarPorId(nrSituacao);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid SituacaoDocumentoDTO situacaoDocumento) {
		return situacaoDocumentoService.cadastrar(situacaoDocumento);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid SituacaoDocumento situacaoDocumento) {
		return situacaoDocumentoService.atualizar(situacaoDocumento);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Max(99) @Min(1) Integer nrSituacao) {
		return situacaoDocumentoService.remover(nrSituacao);
	}

}