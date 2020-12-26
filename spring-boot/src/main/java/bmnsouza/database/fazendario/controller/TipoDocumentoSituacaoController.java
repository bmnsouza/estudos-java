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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.fazendario.entity.TipoDocumentoSituacao;
import bmnsouza.database.fazendario.service.TipoDocumentoSituacaoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/tipoDocumentoSituacao")
public class TipoDocumentoSituacaoController {
	
	@Autowired
	private TipoDocumentoSituacaoService tipoDocumentoSituacaoService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return tipoDocumentoSituacaoService.buscarTodos(pagina);
	}

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(99) Integer cdTipoDocumento, @RequestParam @Min(1) @Max(99) Integer nrSituacao) {
		return tipoDocumentoSituacaoService.buscarPorId(cdTipoDocumento, nrSituacao);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult>cadastrar(@RequestBody @Valid TipoDocumentoSituacao tipoDocumentoSituacao) {		
		return tipoDocumentoSituacaoService.cadastrar(tipoDocumentoSituacao);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult>remover(@RequestParam @Min(1) @Max(99) Integer cdTipoDocumento, @RequestParam @Min(1) @Max(99) Integer nrSituacao) {
		return tipoDocumentoSituacaoService.remover(cdTipoDocumento, nrSituacao);
	}
	
}