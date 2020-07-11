package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.EntidadeTotalBilheteService;

@RestController
@RequestMapping(value = "/entidadeTotalBilhete")
public class EntidadeTotalBilheteController {

  @Autowired
  private EntidadeTotalBilheteService entidadeTotalBilheteService;

  @GetMapping("/buscaPercentualBilhete")
  public ResponseEntity<EntidadeResult> buscaPercentualBilhete(@RequestParam int codSorteio, @RequestParam int pagina) {
    return entidadeTotalBilheteService.buscaPercentualBilhete(codSorteio, pagina);
  }

  @GetMapping("/buscaPercentualIndicacao")
  public ResponseEntity<EntidadeResult> buscaPercentualIndicacao(@RequestParam int codSorteio, @RequestParam int pagina) {
    return entidadeTotalBilheteService.buscaPercentualIndicacao(codSorteio, pagina);
  }

}