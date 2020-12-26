package bmnsouza.database.fazendario.controller;

import java.math.BigInteger;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
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

import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoAtualizarDTO;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoCadastrarDTO;
import bmnsouza.database.fazendario.service.DocumentoArrecadacaoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/documentoArrecadacao")
public class DocumentoArrecadacaoController {

	@Autowired
	private DocumentoArrecadacaoService documentoArrecadacaoService;

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return documentoArrecadacaoService.buscarTodos(pagina);
	}

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @DecimalMin("10000000000000") @DecimalMax("9999999999999999999") @NotNull BigInteger nrDAE) {
		return documentoArrecadacaoService.buscarPorId(nrDAE);
	}
	
	@GetMapping("buscarDAEPorDocumento")
	public ResponseEntity<EntidadeResult> buscarDAEPorDocumento(@RequestParam @DecimalMin("10000000000000") @DecimalMax("9999999999999999999") BigInteger nrDAE) {
		return documentoArrecadacaoService.buscarDAEPorDocumento(nrDAE);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid DocumentoArrecadacaoCadastrarDTO documentoArrecadacaoCadastrarDTO) {
		return documentoArrecadacaoService.cadastrar(documentoArrecadacaoCadastrarDTO);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid DocumentoArrecadacaoAtualizarDTO documentoArrecadacaoAtualizarDTO) {
		return documentoArrecadacaoService.atualizar(documentoArrecadacaoAtualizarDTO);
	}

	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @DecimalMin("10000000000000") @DecimalMax("9999999999999999999") @NotNull BigInteger nrDAE) {
		return documentoArrecadacaoService.remover(nrDAE);
	}
	
}
