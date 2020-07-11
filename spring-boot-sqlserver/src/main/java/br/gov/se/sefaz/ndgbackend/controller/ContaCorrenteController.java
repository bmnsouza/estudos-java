package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.ContaCorrenteService;

@RestController
@RequestMapping(value = "/contaCorrente")
public class ContaCorrenteController {

  @Autowired
  private ContaCorrenteService contaCorrenteService;
  
  @GetMapping("/buscaPorId")
  public ResponseEntity<EntidadeResult> buscaPorId(@RequestParam Integer id) {
    return contaCorrenteService.buscaPorId(id);
  }

  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf) {
    return contaCorrenteService.buscaPorCpf(cpf);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return contaCorrenteService.buscaTodos(pagina);
  }

}