package br.gov.se.sefaz.ndgbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.model.UsuarioModel;
import br.gov.se.sefaz.ndgbackend.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;
  
  @GetMapping("/buscaPorCpf")
  public ResponseEntity<EntidadeResult> buscaPorCpf(@RequestParam String cpf) {
    return usuarioService.buscaPorCpf(cpf);
  }

  @GetMapping("/buscaPorNome")
  public ResponseEntity<EntidadeResult> buscaPorNome(@RequestParam String nome, @RequestParam int pagina) {
    return usuarioService.buscaPorNome(nome, pagina);
  }

  @GetMapping("/buscaPorPerfil")
  public ResponseEntity<EntidadeResult> buscaPorPerfil(@RequestParam int codPerfil, @RequestParam int pagina) {
    return usuarioService.buscaPorPerfil(codPerfil, pagina);
  }

  @GetMapping("/buscaPorStatus")
  public ResponseEntity<EntidadeResult> buscaPorStatus(@RequestParam int status, @RequestParam int pagina) {
    return usuarioService.buscaPorStatus(status, pagina);
  }

  @GetMapping("/buscaTodos")
  public ResponseEntity<EntidadeResult> buscaTodos(@RequestParam int pagina) {
    return usuarioService.buscaTodos(pagina);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<EntidadeResult> cadastra(@RequestBody UsuarioModel usuarioModel) {
    return usuarioService.cadastra(usuarioModel);
  }

  @PutMapping
  public ResponseEntity<EntidadeResult> atualiza(@RequestBody UsuarioModel usuarioModel) {
    return usuarioService.atualiza(usuarioModel);
  }

}