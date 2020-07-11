package br.gov.se.sefaz.ndgbackend.controller;

import java.util.List;

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
import br.gov.se.sefaz.ndgbackend.model.SorteioPremioModel;
import br.gov.se.sefaz.ndgbackend.service.SorteioPremioService;

@RestController
@RequestMapping(value = "/sorteioPremio")
public class SorteioPremioController {

  @Autowired
  private SorteioPremioService sorteioPremioService;

  @GetMapping("/buscaPorSorteio")
  public ResponseEntity<EntidadeResult> buscaPorSorteio(@RequestParam Integer codSorteio, @RequestParam int pagina) {
    return sorteioPremioService.buscaPorSorteio(codSorteio, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return sorteioPremioService.buscaTodos(pagina);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody List<SorteioPremioModel> sorteioPremioModel) {
    return sorteioPremioService.cadastra(sorteioPremioModel);
  }

  @PutMapping
  public ResponseEntity<EntidadeResult> transfere(@RequestParam Integer codSorteio) {
    return sorteioPremioService.transfere(codSorteio);
  }

}