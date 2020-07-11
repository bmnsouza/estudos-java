package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.model.BloqueioResgateModel;
import br.gov.se.sefaz.ndgbackend.service.BloqueioResgateService;

@RestController
@RequestMapping(value = "/bloqueioResgate")
public class BloqueioResgateController {

  @Autowired
  private BloqueioResgateService bloqueioResgateService;

  @GetMapping("/buscaPorData")
  public ResponseEntity<EntidadeResult> buscaPorData(@RequestParam String dataBloqueio) throws TransactionAbortException {
    return bloqueioResgateService.buscaPorData(dataBloqueio);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return bloqueioResgateService.buscaTodos(pagina);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody BloqueioResgateModel bloqueioResgateModel) throws TransactionAbortException {
    return bloqueioResgateService.cadastra(bloqueioResgateModel);
  }

  @PutMapping
  public ResponseEntity<EntidadeResult> atualiza(@RequestBody BloqueioResgateModel bloqueioResgateModel) throws TransactionAbortException {
    return bloqueioResgateService.atualiza(bloqueioResgateModel);
  }

  @DeleteMapping
  public ResponseEntity<EntidadeResult> exclui(@RequestParam String dataBloqueio) throws TransactionAbortException {
    return bloqueioResgateService.exclui(dataBloqueio);
  }

}