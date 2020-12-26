package bmnsouza.database.fazendario.controller;

import java.math.BigInteger;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.fazendario.entity.DetalheDocumentoArrecadacao;
import bmnsouza.database.fazendario.service.DetalheDocumentoArrecadacaoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/detalheDocumentoArrecadacao")
public class DetalheDocumentoArrecadacaoController {
	
	@Autowired
	private DetalheDocumentoArrecadacaoService detalheDocumentoArrecadacaoService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return detalheDocumentoArrecadacaoService.buscarTodos(pagina);
	}

	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(
			@RequestParam @Positive @DecimalMin("10000000000000") @DecimalMax("9999999999999999999") @NotNull BigInteger nrDAE,
			@Max(999) @Min(0) @NotNull Integer nrItem) {
		return detalheDocumentoArrecadacaoService.buscarPorId(nrDAE, nrItem);
	}

	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid DetalheDocumentoArrecadacao detalheDocumentoArrecadacao) {
		return detalheDocumentoArrecadacaoService.atualizar(detalheDocumentoArrecadacao);
	}

}
