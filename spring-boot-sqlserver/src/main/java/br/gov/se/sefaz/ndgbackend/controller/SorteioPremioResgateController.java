package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.SorteioPremioResgateService;

@RestController
@RequestMapping(value = "/sorteioPremioResgate")
public class SorteioPremioResgateController {

  @Autowired
  private SorteioPremioResgateService sorteioPremioResgateService;

  @GetMapping("/buscaExpirados")
  public ResponseEntity<EntidadeResult> buscaExpirados(@RequestParam int pagina) {
    return sorteioPremioResgateService.buscaExpirados(pagina);
  }

  @GetMapping("/buscaNaoExpirados")
  public ResponseEntity<EntidadeResult> buscaNaoExpirados() {
    return sorteioPremioResgateService.buscaNaoExpirados();
  }

}