package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.model.ConsumidorModel;
import br.gov.se.sefaz.ndgbackend.service.ConsumidorService;

@RestController
@RequestMapping(value = "/consumidor")
public class ConsumidorController {

  @Autowired
  private ConsumidorService consumidorService;

  @GetMapping("/buscaPorId")
  public ResponseEntity<EntidadeResult> buscaPorId(@RequestParam Integer id) {
    return consumidorService.buscaPorId(id);
  }

  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf) {
    return consumidorService.buscaPorCpf(cpf);
  }

  @GetMapping("/buscaPorNome")
  public ResponseEntity<EntidadeResult> buscaPorNome(@RequestParam String nome, @RequestParam int pagina) {
    return consumidorService.buscaPorNome(nome, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return consumidorService.buscaTodos(pagina);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody ConsumidorModel consumidorModel) throws TransactionAbortException {
    return consumidorService.cadastra(consumidorModel);
  }

  @PutMapping
  public ResponseEntity<EntidadeResult> atualiza(@RequestBody ConsumidorModel consumidorModel) throws Exception {
    return consumidorService.atualiza(consumidorModel);
  }

}