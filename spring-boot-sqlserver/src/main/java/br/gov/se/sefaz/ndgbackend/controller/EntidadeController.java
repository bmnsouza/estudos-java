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
import br.gov.se.sefaz.ndgbackend.model.EntidadeModel;
import br.gov.se.sefaz.ndgbackend.service.EntidadeService;

@RestController
@RequestMapping(value = "/entidade")
public class EntidadeController {

  @Autowired
  private EntidadeService entidadeService;

  @GetMapping("/buscaPorId")
  public ResponseEntity<EntidadeResult> buscaPorId(@RequestParam Integer id) {
    return entidadeService.buscaPorId(id);
  }

  @GetMapping("/buscaPorCnpj")
  public ResponseEntity<EntidadeResult> buscaPorCnpj(@RequestParam String cnpj) {
    return entidadeService.buscaPorCnpj(cnpj);
  }

  @GetMapping("/buscaPorRazao")
  public ResponseEntity<EntidadeResult> buscaPorRazao(@RequestParam String razao, @RequestParam int pagina) {
    return entidadeService.buscaPorRazao(razao, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return entidadeService.buscaTodos(pagina);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody EntidadeModel entidadeModel) {
    return entidadeService.cadastra(entidadeModel);
  }

  @PutMapping
  public ResponseEntity<EntidadeResult> atualiza(@RequestBody EntidadeModel entidadeModel) {
    return entidadeService.atualiza(entidadeModel);
  }

}