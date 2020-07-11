package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.TransferenciaService;

@RestController
@RequestMapping(value = "/transferencia")
public class TransferenciaController {

  @Autowired
  private TransferenciaService transferenciaService;
  
  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf, @RequestParam int pagina) {
    return transferenciaService.buscaPorCpf(cpf, pagina);
  }

  @GetMapping("/buscaPorDataRelatorio")
  public ResponseEntity<EntidadeResult> buscaPorDataRelatorio(@RequestParam String dataRelatorio, @RequestParam int pagina) throws TransactionAbortException {
    return transferenciaService.buscaPorDataRelatorio(dataRelatorio, pagina);
  }

  @GetMapping("/buscaPorStatus")
  public ResponseEntity<EntidadeResult> buscaPorStatus(@RequestParam int status, @RequestParam int pagina) {
    return transferenciaService.buscaPorStatus(status, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return transferenciaService.buscaTodos(pagina);
  }

  @PutMapping("/atualizaParaInvalida")
  public ResponseEntity<EntidadeResult> atualizaParaInvalida(@RequestParam Integer id) {
    return transferenciaService.atualizaParaInvalida(id);
  }

  @PutMapping("/atualizaParaConferencia")
  public ResponseEntity<EntidadeResult> atualizaParaConferencia() {
    return transferenciaService.atualizaParaConferencia();
  }

  @PutMapping("/atualizaParaResgate")
  public ResponseEntity<EntidadeResult> atualizaParaResgate() {
    return transferenciaService.atualizaParaResgate();
  }

}