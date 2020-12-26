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

import bmnsouza.database.fazendario.entity.MotivoAlteracaoDocumento;
import bmnsouza.database.fazendario.entity.dto.motivoAlteracaoDocumento.MotivoAlteracaoDocumentoDTO;
import bmnsouza.database.fazendario.service.MotivoAlteracaoDocumentoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/motivoAlteracaoDocumento")
public class MotivoAlteracaoDocumentoController {
	
	@Autowired
	private MotivoAlteracaoDocumentoService motivoAlteracaoDocumentoService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return motivoAlteracaoDocumentoService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(999) @NotNull Integer cdMotivo) {
		return motivoAlteracaoDocumentoService.buscarPorId(cdMotivo);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid MotivoAlteracaoDocumentoDTO motivoAlteracaoDocumento){
		return motivoAlteracaoDocumentoService.cadastrar(motivoAlteracaoDocumento);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid MotivoAlteracaoDocumento motivoAlteracaoDocumento) {
		return motivoAlteracaoDocumentoService.atualizar(motivoAlteracaoDocumento);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(999) @NotNull Integer cdMotivo) {
		return motivoAlteracaoDocumentoService.remover(cdMotivo);
	}
	
}
