package	bmnsouza.database.nota.controller;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.nota.service.DocumentoFiscalService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/documentoFiscal")
public class DocumentoFiscalController {

	@Autowired
	private DocumentoFiscalService documentoFiscalService;

	@GetMapping("buscarPorNotaFiscal")
	public ResponseEntity<EntidadeResult> buscarPorNotaFiscal(@RequestParam @NotEmpty String numeroNotaFiscal) {
		return documentoFiscalService.buscarPorNotaFiscal(numeroNotaFiscal);
	}

	@GetMapping("buscarPorEmitente")
	public ResponseEntity<EntidadeResult> buscarPorEmitente(@RequestParam @CNPJ String cnpjEmitente, @RequestParam LocalDate dataEmissaoInicio,
		@RequestParam LocalDate dataEmissaoFim, @RequestParam @PositiveOrZero int pagina) {
		return documentoFiscalService.buscarPorEmitente(cnpjEmitente, dataEmissaoInicio, dataEmissaoFim, pagina);
	}

	@GetMapping("buscarPorDestinatario")
	public ResponseEntity<EntidadeResult> buscarPorDestinatario(@RequestParam @CPF String cpfDestinatario, @RequestParam LocalDate dataEmissaoInicio,
		@RequestParam LocalDate dataEmissaoFim, @RequestParam @PositiveOrZero int pagina) {
		return documentoFiscalService.buscarPorDestinatario(cpfDestinatario, dataEmissaoInicio, dataEmissaoFim, pagina);
	}

	@GetMapping("buscarPorEmitenteDestinatario")
	public ResponseEntity<EntidadeResult> buscarPorEmitenteDestinatario(@RequestParam @CNPJ String cnpjEmitente, @RequestParam @CPF String cpfDestinatario,
		@RequestParam LocalDate dataEmissaoInicio, @RequestParam LocalDate dataEmissaoFim, @RequestParam @PositiveOrZero int pagina) {
		return documentoFiscalService.buscarPorEmitenteDestinatario(cnpjEmitente, cpfDestinatario, dataEmissaoInicio, dataEmissaoFim, pagina);
	}

}