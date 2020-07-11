package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.DocumentoFiscalService;

@RestController
@RequestMapping(value = "/documentoFiscal")
public class DocumentoFiscalController {

  @Autowired
  private DocumentoFiscalService documentoFiscalService;

  @GetMapping("/buscaPorNotaFiscal")
  public ResponseEntity<EntidadeResult> buscaPorNotaFiscal(@RequestParam String numeroNotaFiscal) {
    return documentoFiscalService.buscaPorNotaFiscal(numeroNotaFiscal);
  }

  @GetMapping("/buscaPorEmitente")
  public ResponseEntity<EntidadeResult> buscaPorEmitente(@RequestParam String cnpjEmitente, @RequestParam String dataEmissaoInicio,
    @RequestParam String dataEmissaoFim, @RequestParam int pagina) throws TransactionAbortException {
    return documentoFiscalService.buscaPorEmitente(cnpjEmitente, dataEmissaoInicio, dataEmissaoFim, pagina);
  }

  @GetMapping("/buscaPorDestinatario")
  public ResponseEntity<EntidadeResult> buscaPorDestinatario(@RequestParam String cpfDestinatario, @RequestParam String dataEmissaoInicio,
    @RequestParam String dataEmissaoFim, @RequestParam int pagina) throws TransactionAbortException {
    return documentoFiscalService.buscaPorDestinatario(cpfDestinatario, dataEmissaoInicio, dataEmissaoFim, pagina);
  }

  @GetMapping("/buscaPorEmitenteDestinatario")
  public ResponseEntity<EntidadeResult> buscaPorEmitenteDestinatario(@RequestParam String cnpjEmitente, @RequestParam String cpfDestinatario,
    @RequestParam String dataEmissaoInicio, @RequestParam String dataEmissaoFim, @RequestParam int pagina) throws TransactionAbortException {
    return documentoFiscalService.buscaPorEmitenteDestinatario(cnpjEmitente, cpfDestinatario, dataEmissaoInicio, dataEmissaoFim, pagina);
  }

}