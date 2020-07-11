package br.gov.se.sefaz.ndgbackend.service;

import java.math.BigDecimal;

import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.gov.se.sefaz.ndgbackend.core.Data;
import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.OmissaoModel;
import br.gov.se.sefaz.ndgbackend.repository.OmissaoRepository;

@Service
@Validated
public class OmissaoService {

  @Autowired
  private OmissaoRepository omissaoRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorIe(String ie, @PositiveOrZero int pagina) {
    Slice<OmissaoModel> slice = omissaoRepository.buscaPorIe(ie, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorCnpj(@CNPJ String cnpj, @PositiveOrZero int pagina) {
    Slice<OmissaoModel> slice = omissaoRepository.buscaPorCnpj(cnpj, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorAnoMes(int ano, int mes, @PositiveOrZero int pagina) {
    Slice<OmissaoModel> slice = omissaoRepository.buscaPorAnoMes(ano, mes, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorRbaMenorQue(BigDecimal rba, @PositiveOrZero int pagina) {
    Slice<OmissaoModel> slice = omissaoRepository.buscaPorRbaMenorQue(rba, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorRbaIgualA(BigDecimal rba, @PositiveOrZero int pagina) {
    Slice<OmissaoModel> slice = omissaoRepository.buscaPorRbaIgualA(rba, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorRbaMaiorQue(BigDecimal rba, @PositiveOrZero int pagina) {
    Slice<OmissaoModel> slice = omissaoRepository.buscaPorRbaMaiorQue(rba, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorDataEntrada(String dataEntrada, @PositiveOrZero int pagina) throws TransactionAbortException {
    if (!Data.isValida(dataEntrada)) {
      throw new TransactionAbortException("dataEntrada", "inválida");
    }

    Slice<OmissaoModel> slice = omissaoRepository.buscaPorDataEntrada(dataEntrada, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<OmissaoModel> slice = omissaoRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  @DeleteMapping
  public ResponseEntity<EntidadeResult> exclui(String ie, @CNPJ String cnpj, int ano, int mes, @CPF String cpfResponsavel, String protocolo) {
    int linhasAfetadas = omissaoRepository.exclui(ie, cnpj, ano, mes, cpfResponsavel, protocolo);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}