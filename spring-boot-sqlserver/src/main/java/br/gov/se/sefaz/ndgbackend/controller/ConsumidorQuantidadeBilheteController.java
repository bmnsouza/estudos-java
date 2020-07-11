package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.ConsumidorQuantidadeBilheteService;

@RestController
@RequestMapping(value = "/consumidorQuantidadeBilhete")
public class ConsumidorQuantidadeBilheteController {

  @Autowired
  private ConsumidorQuantidadeBilheteService consumidorQuantidadeBilheteService;

  @GetMapping("/buscaPorSorteio")
  public ResponseEntity<EntidadeResult> buscaPorSorteio(@RequestParam int codSorteio, @RequestParam int pagina) {
    return consumidorQuantidadeBilheteService.buscaPorSorteio(codSorteio, pagina);
  }

}