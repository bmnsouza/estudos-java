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

import bmnsouza.annotation.Ano;
import bmnsouza.database.fazendario.entity.dto.vencimentoIcms.VencimentoIcmsAtualizarDTO;
import bmnsouza.database.fazendario.entity.dto.vencimentoIcms.VencimentoIcmsCadastrarDTO;
import bmnsouza.database.fazendario.service.VencimentoIcmsService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/vencimentoIcms")
public class VencimentoIcmsController {

	@Autowired
	private VencimentoIcmsService vencimentoIcmsService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return vencimentoIcmsService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(
			@RequestParam @Max(9999) @Min(1) @NotNull Integer nrSequencialVencimentoIcms,
			@RequestParam @Ano @NotNull Integer nrAno) {
		return vencimentoIcmsService.buscarPorId(nrSequencialVencimentoIcms, nrAno);
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid VencimentoIcmsCadastrarDTO vencimentoIcmsCadastrarDTO) {
		return vencimentoIcmsService.cadastrar(vencimentoIcmsCadastrarDTO);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid VencimentoIcmsAtualizarDTO vencimentoIcmsAtualizarDTO) {
		return vencimentoIcmsService.atualizar(vencimentoIcmsAtualizarDTO);
	}

	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(
			@RequestParam @Max(9999) @Min(1) @NotNull Integer nrSequencialVencimentoIcms,
			@RequestParam @Ano @NotNull Integer nrAno) {
		return vencimentoIcmsService.remover(nrSequencialVencimentoIcms, nrAno);
	}
	
}