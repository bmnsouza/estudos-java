package br.gov.se.sefaz.ndgbackend.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.OmissaoService;

@RestController
@RequestMapping(value = "/omissao")
public class OmissaoController {

  @Autowired
  private OmissaoService omissaoService;

  @GetMapping("/buscaPorIe")
  public ResponseEntity<EntidadeResult> buscaPorIe(@RequestParam String ie, @RequestParam int pagina) {
    return omissaoService.buscaPorIe(ie, pagina);
  }

  @GetMapping("/buscaPorCnpj")
  public ResponseEntity<EntidadeResult> buscaPorCnpj(@RequestParam String cnpj, @RequestParam int pagina) {
    return omissaoService.buscaPorCnpj(cnpj, pagina);
  }

  @GetMapping("/buscaPorAnoMes")
  public ResponseEntity<EntidadeResult> buscaPorAnoMes(@RequestParam int ano, @RequestParam int mes, @RequestParam int pagina) {
    return omissaoService.buscaPorAnoMes(ano, mes, pagina);
  }

  @GetMapping("/buscaPorRbaMenorQue")
  public ResponseEntity<EntidadeResult> buscaPorRbaMenorQue(@RequestParam BigDecimal rba, @RequestParam int pagina) {
    return omissaoService.buscaPorRbaMenorQue(rba, pagina);
  }

  @GetMapping("/buscaPorRbaIgualA")
  public ResponseEntity<EntidadeResult> buscaPorRbaIgualA(@RequestParam BigDecimal rba, @RequestParam int pagina) {
    return omissaoService.buscaPorRbaIgualA(rba, pagina);
  }

  @GetMapping("/buscaPorRbaMaiorQue")
  public ResponseEntity<EntidadeResult> buscaPorRbaMaiorQue(@RequestParam BigDecimal rba, @RequestParam int pagina) {
    return omissaoService.buscaPorRbaMaiorQue(rba, pagina);
  }

  @GetMapping("/buscaPorDataEntrada")
  public ResponseEntity<EntidadeResult> buscaPorDataEntrada(@RequestParam String dataEntrada, @RequestParam int pagina) throws TransactionAbortException {
    return omissaoService.buscaPorDataEntrada(dataEntrada, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return omissaoService.buscaTodos(pagina);
  }

  @DeleteMapping
  public ResponseEntity<EntidadeResult> exclui(@RequestParam String ie, @RequestParam String cnpj, @RequestParam int ano, @RequestParam int mes,
    @RequestParam String cpfResponsavel, @RequestParam String protocolo) {
    return omissaoService.exclui(ie, cnpj, ano, mes, cpfResponsavel, protocolo);
  }

  // @DeleteMapping
  // public ResponseEntity<EntidadeResult> exclui(@RequestBody OmissaoModel omissaoModel) {
  //   return omissaoService.exclui(omissaoModel);
  // }

}