package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.ResgateService;

@RestController
@RequestMapping(value = "/resgate")
public class ResgateController {

  @Autowired
  private ResgateService resgateService;

  @GetMapping("/buscaConferencia")
  public ResponseEntity<EntidadeResult> buscaConferencia(@RequestParam int pagina) {
    return resgateService.buscaConferencia(pagina);
  }

  @GetMapping("/buscaRealizado")
  public ResponseEntity<EntidadeResult> buscaRealizado(@RequestParam String dataRelatorio, @RequestParam int pagina) throws TransactionAbortException {
    return resgateService.buscaRealizado(dataRelatorio, pagina);
  }

  @GetMapping("/buscaNaoRealizado")
  public ResponseEntity<EntidadeResult> buscaNaoRealizado(@RequestParam int pagina) {
    return resgateService.buscaNaoRealizado(pagina);
  }

}