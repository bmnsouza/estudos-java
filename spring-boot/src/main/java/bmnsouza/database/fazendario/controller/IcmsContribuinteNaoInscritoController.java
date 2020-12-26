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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.fazendario.entity.IcmsContribuinteNaoInscrito;
import bmnsouza.database.fazendario.service.IcmsContribuinteNaoInscritoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/icmsContribuinteNaoInscrito")
public class IcmsContribuinteNaoInscritoController {

	@Autowired
	private IcmsContribuinteNaoInscritoService icmsContribuinteNaoInscritoService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return icmsContribuinteNaoInscritoService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(99) @NotNull Integer nrObjetoReferencia, @RequestParam @Min(1) @Max(99) @NotNull Integer nrFormaRecolhimentoIcms) {
		return icmsContribuinteNaoInscritoService.buscarPorId(nrObjetoReferencia, nrFormaRecolhimentoIcms);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid IcmsContribuinteNaoInscrito icmsContribuinteNaoInscrito) {
		return icmsContribuinteNaoInscritoService.cadastrar(icmsContribuinteNaoInscrito);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(99) @NotNull Integer nrObjetoReferencia, @RequestParam @Min(1) @Max(99) @NotNull Integer nrFormaRecolhimentoIcms) {
		return icmsContribuinteNaoInscritoService.remover(nrObjetoReferencia, nrFormaRecolhimentoIcms);
	}
	
}