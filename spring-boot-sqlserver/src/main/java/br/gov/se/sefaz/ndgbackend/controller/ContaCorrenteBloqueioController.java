package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.model.ContaCorrenteBloqueioModel;
import br.gov.se.sefaz.ndgbackend.service.ContaCorrenteBloqueioService;

@RestController
@RequestMapping(value = "/contaCorrenteBloqueio")
public class ContaCorrenteBloqueioController {

  @Autowired
  private ContaCorrenteBloqueioService contaCorrenteBloqueioService;
  
  @GetMapping("/buscaPorId")
  public ResponseEntity<EntidadeResult> buscaPorId(@RequestParam Integer id) {
    return contaCorrenteBloqueioService.buscaPorId(id);
  }

  @GetMapping("/buscaPorContaCorrente")
  public ResponseEntity<EntidadeResult> buscaPorContaCorrente(@RequestParam Integer idContaCorrente, @RequestParam int pagina) {
    return contaCorrenteBloqueioService.buscaPorContaCorrente(idContaCorrente, pagina);
  }

  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf, @RequestParam int pagina) {
    return contaCorrenteBloqueioService.buscaPorCpf(cpf, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return contaCorrenteBloqueioService.buscaTodos(pagina);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody ContaCorrenteBloqueioModel contaCorrenteBloqueioModel) {
    return contaCorrenteBloqueioService.cadastra(contaCorrenteBloqueioModel);
  }

}