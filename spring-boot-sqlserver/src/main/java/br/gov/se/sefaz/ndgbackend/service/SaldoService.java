package br.gov.se.sefaz.ndgbackend.service;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.SaldoModel;
import br.gov.se.sefaz.ndgbackend.repository.SaldoRepository;

@Service
@Validated
public class SaldoService {

  @Autowired
  private SaldoRepository saldoRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorId(@Positive Integer id) {
    SaldoModel saldoModel = saldoRepository.buscaPorId(id);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, saldoModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorCpf(@CPF String cpf) {
    SaldoModel saldoModel = saldoRepository.buscaPorCpf(cpf);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, saldoModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorSaldoMenorQue(BigDecimal saldo, @PositiveOrZero int pagina) {
    Slice<SaldoModel> slice = saldoRepository.buscaPorSaldoMenorQue(saldo, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorSaldoIgualA(BigDecimal saldo, int pagina) {
    Slice<SaldoModel> slice = saldoRepository.buscaPorSaldoIgualA(saldo, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorSaldoMaiorQue(BigDecimal saldo, @PositiveOrZero int pagina) {
    Slice<SaldoModel> slice = saldoRepository.buscaPorSaldoMaiorQue(saldo, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<SaldoModel> slice = saldoRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }
}