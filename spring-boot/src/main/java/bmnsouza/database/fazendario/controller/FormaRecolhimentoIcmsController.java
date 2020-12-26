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

import bmnsouza.database.fazendario.entity.FormaRecolhimentoIcms;
import bmnsouza.database.fazendario.entity.dto.formaRecolhimentoIcms.FormaRecolhimentoIcmsDTO;
import bmnsouza.database.fazendario.service.FormaRecolhimentoIcmsService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/formaRecolhimentoIcms")
public class FormaRecolhimentoIcmsController {

	@Autowired
	private FormaRecolhimentoIcmsService formaRecolhimentoIcmsService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return formaRecolhimentoIcmsService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(99) @NotNull Integer nrFormaRecolhimentoIcms) {
		return formaRecolhimentoIcmsService.buscarPorId(nrFormaRecolhimentoIcms);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid FormaRecolhimentoIcmsDTO formaRecolhimentoIcmsDTO) {
		return formaRecolhimentoIcmsService.cadastrar(formaRecolhimentoIcmsDTO);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid FormaRecolhimentoIcms formaRecolhimentoIcms) {
		return formaRecolhimentoIcmsService.atualizar(formaRecolhimentoIcms);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(99) @NotNull Integer nrFormaRecolhimentoIcms) {
		return formaRecolhimentoIcmsService.remover(nrFormaRecolhimentoIcms);
	}
	
}