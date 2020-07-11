package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.FazendarioPortalService;

@RestController
@RequestMapping(value = "/fazendarioPortal")
public class FazendarioPortalController {

  @Autowired
  private FazendarioPortalService fazendarioPortalService;

  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf) {
    return fazendarioPortalService.buscaPorCpf(cpf);
  }

  @GetMapping("/buscaPorNome")
  public ResponseEntity<EntidadeResult>buscaPorNome(@RequestParam String nome) {
    return fazendarioPortalService.buscaPorNome(nome);
  }

}