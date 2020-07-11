package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.model.SorteioModel;
import br.gov.se.sefaz.ndgbackend.service.SorteioService;

@RestController
@RequestMapping(value = "/sorteio")
public class SorteioController {

  @Autowired
  private SorteioService sorteioService;

  @GetMapping("/buscaPorCodigo")
  public ResponseEntity<EntidadeResult> buscaPorCodigo(@RequestParam Integer codSorteio) {
    return sorteioService.buscaPorCodigo(codSorteio);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return sorteioService.buscaTodos(pagina);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody SorteioModel sorteioModel) throws TransactionAbortException {
    return sorteioService.cadastra(sorteioModel);
  }

}