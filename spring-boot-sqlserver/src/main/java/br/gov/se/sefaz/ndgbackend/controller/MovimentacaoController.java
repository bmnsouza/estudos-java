package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.MovimentacaoService;

@RestController
@RequestMapping(value = "/movimentacao")
public class MovimentacaoController {

  @Autowired
  private MovimentacaoService movimentacaoService;

  @GetMapping("/buscaPorContaCorrente")
  public ResponseEntity<EntidadeResult> buscaPorContaCorrente(@RequestParam Integer idContaCorrente, @RequestParam int pagina) {
    return movimentacaoService.buscaPorContaCorrente(idContaCorrente, pagina);
  }

  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf, @RequestParam int pagina) {
    return movimentacaoService.buscaPorCpf(cpf, pagina);
  }

  @GetMapping("/buscaPorSorteio")
  public ResponseEntity<EntidadeResult> buscaPorSorteio(@RequestParam Integer codSorteio, @RequestParam int pagina) {
    return movimentacaoService.buscaPorSorteio(codSorteio, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return movimentacaoService.buscaTodos(pagina);
  }

}