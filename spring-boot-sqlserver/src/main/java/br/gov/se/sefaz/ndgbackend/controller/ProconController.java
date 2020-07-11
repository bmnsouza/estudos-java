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

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.model.ProconModel;
import br.gov.se.sefaz.ndgbackend.service.ProconService;

@RestController
@RequestMapping(value = "/procon")
public class ProconController {

  @Autowired
  private ProconService proconService;

  @GetMapping("/buscaPorId")
  public ResponseEntity<EntidadeResult> buscaPorId(@RequestParam Integer id) {
    return proconService.buscaPorId(id);
  }

  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf) {
    return proconService.buscaPorCpf(cpf);
  }

  @GetMapping("/buscaPorNome")
  public ResponseEntity<EntidadeResult> buscaPorNome(@RequestParam String nome, @RequestParam int pagina) {
    return proconService.buscaPorNome(nome, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return proconService.buscaTodos(pagina);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody ProconModel proconModel) {
    return proconService.cadastra(proconModel);
  }

  @PutMapping
  public ResponseEntity<EntidadeResult> atualiza(@RequestBody ProconModel proconModel) {
    return proconService.atualiza(proconModel);
  }

}