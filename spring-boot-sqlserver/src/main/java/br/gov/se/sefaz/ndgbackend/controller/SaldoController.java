package br.gov.se.sefaz.ndgbackend.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.SaldoService;

@RestController
@RequestMapping(value = "/saldo")
public class SaldoController {

  @Autowired
  private SaldoService saldoService;
  
  @GetMapping("/buscaPorId")
  public ResponseEntity<EntidadeResult> buscaPorId(@RequestParam Integer id) {
    return saldoService.buscaPorId(id);
  }

  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf) {
    return saldoService.buscaPorCpf(cpf);
  }

  @GetMapping("/buscaPorSaldoMenorQue")
  public ResponseEntity<EntidadeResult> buscaPorSaldoMenorQue(@RequestParam BigDecimal saldo, @RequestParam int pagina) {
    return saldoService.buscaPorSaldoMenorQue(saldo, pagina);
  }

  @GetMapping("/buscaPorSaldoIgualA")
  public ResponseEntity<EntidadeResult> buscaPorSaldoIgualA(@RequestParam BigDecimal saldo, @RequestParam int pagina) {
    return saldoService.buscaPorSaldoIgualA(saldo, pagina);
  }

  @GetMapping("/buscaPorSaldoMaiorQue")
  public ResponseEntity<EntidadeResult> buscaPorSaldoMaiorQue(@RequestParam BigDecimal saldo, @RequestParam int pagina) {
    return saldoService.buscaPorSaldoMaiorQue(saldo, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return saldoService.buscaTodos(pagina);
  }

}