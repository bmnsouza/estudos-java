package br.gov.se.sefaz.ndgbackend.controller;

import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.service.BilheteService;

@RestController
@RequestMapping(value = "/bilhete")
public class BilheteController {

  @Autowired
  private BilheteService bilheteService;

  @GetMapping("/buscaPremiadosPorSorteio")
  public ResponseEntity<EntidadeResult> buscaPremiadosPorSorteio(@RequestParam Integer codSorteio, @RequestParam int pagina) {
    return bilheteService.buscaPremiadosPorSorteio(codSorteio, pagina);
  }

  @GetMapping("/buscaConsumidorPorSorteio")
  public ResponseEntity<EntidadeResult> buscaConsumidorPorSorteio(@RequestParam Integer codSorteio, @RequestParam String cpfConsumidor, @RequestParam int pagina) {
    return bilheteService.buscaConsumidorPorSorteio(codSorteio, cpfConsumidor, pagina);
  }

  @GetMapping("/buscaEntidadePorSorteio")
  public ResponseEntity<EntidadeResult> buscaEntidadePorSorteio(@RequestParam Integer codSorteio, @RequestParam String cnpjEntidade, @RequestParam int pagina) {
    return bilheteService.buscaEntidadePorSorteio(codSorteio, cnpjEntidade, pagina);
  }

  @GetMapping("/buscaPorSorteio")
  public ResponseEntity<EntidadeResult> buscaPorSorteio(@RequestParam Integer codSorteio, @RequestParam int pagina) {
    return bilheteService.buscaPorSorteio(codSorteio, pagina);
  }

  @GetMapping("/buscaQuantidadePorDatas")
  public ResponseEntity<EntidadeResult> buscaQuantidadePorDatas(@RequestParam String dataInicio, @RequestParam String dataFim) throws TransactionAbortException {
    return bilheteService.buscaQuantidadePorDatas(dataInicio, dataFim);
  }

  @PostMapping
  public ResponseEntity<EntidadeResult> cadastra(@RequestParam Integer codSorteio) throws SQLException, SQLServerException, TransactionAbortException {
    return bilheteService.gera(codSorteio);
  }

  @PutMapping
  public ResponseEntity<EntidadeResult> sorteia(@RequestParam Integer codSorteio, @RequestParam Integer concursoLoteria, @RequestParam String premioLoteria1,
    @RequestParam String premioLoteria2, @RequestParam String premioLoteria3, @RequestParam String premioLoteria4, @RequestParam String premioLoteria5)
    throws TransactionAbortException {
    return bilheteService.sorteia(codSorteio, concursoLoteria, premioLoteria1, premioLoteria2, premioLoteria3, premioLoteria4, premioLoteria5);
  }

}