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

import bmnsouza.database.fazendario.entity.IcmsContribuinteInscrito;
import bmnsouza.database.fazendario.entity.dto.icmsContribuinteInscrito.IcmsContribuinteInscritoDTO;
import bmnsouza.database.fazendario.service.IcmsContribuinteInscritoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/icmsContribuinteInscrito")
public class IcmsContribuinteInscritoController {

	@Autowired
	private IcmsContribuinteInscritoService icmsContribuinteInscritoService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return icmsContribuinteInscritoService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(99) @NotNull Integer tpContribuinte,
			@RequestParam @Min(1) @Max(99) @NotNull Integer nrRegimeTributacaoIcms, @RequestParam  @Min(1) @Max(99) @NotNull Integer nrFormaRecolhimentoIcms) {
		return icmsContribuinteInscritoService.buscarPorId(tpContribuinte, nrRegimeTributacaoIcms, nrFormaRecolhimentoIcms);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid IcmsContribuinteInscritoDTO icmsContribuinteInscrito) {
		return icmsContribuinteInscritoService.cadastrar(icmsContribuinteInscrito);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid IcmsContribuinteInscrito icmsContribuinteInscrito){
		return icmsContribuinteInscritoService.atualizar(icmsContribuinteInscrito);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(99) @NotNull Integer tpContribuinte,
			@RequestParam @Min(1) @Max(99) @NotNull Integer nrRegimeTributacaoIcms, @RequestParam @Min(1) @Max(99) @NotNull Integer nrFormaRecolhimentoIcms) {
		return icmsContribuinteInscritoService.remover(tpContribuinte, nrRegimeTributacaoIcms, nrFormaRecolhimentoIcms);
	}
	
}