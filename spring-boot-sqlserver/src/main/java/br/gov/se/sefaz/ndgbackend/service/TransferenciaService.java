package br.gov.se.sefaz.ndgbackend.service;

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

import br.gov.se.sefaz.ndgbackend.core.Data;
import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.TransferenciaModel;
import br.gov.se.sefaz.ndgbackend.repository.TransferenciaRepository;

@Service
@Validated
public class TransferenciaService {

  @Autowired
  private TransferenciaRepository transferenciaRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorCpf(@CPF String cpf, @PositiveOrZero int pagina) {
    Slice<TransferenciaModel> slice = transferenciaRepository.buscaPorCpf(cpf, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorDataRelatorio(String dataRelatorio, @PositiveOrZero int pagina) throws TransactionAbortException {
    if (!Data.isValida(dataRelatorio)) {
      throw new TransactionAbortException("dataRelatorio", "inválida");
    }

    Slice<TransferenciaModel> slice = transferenciaRepository.buscaPorDataRelatorio(dataRelatorio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorStatus(int status, @PositiveOrZero int pagina) {
    Slice<TransferenciaModel> slice = transferenciaRepository.buscaPorStatus(status, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<TransferenciaModel> slice = transferenciaRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> atualizaParaInvalida(@Positive Integer id) {
    int linhasAfetadas = transferenciaRepository.atualizaParaInvalida(id);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> atualizaParaConferencia() {
    int linhasAfetadas = transferenciaRepository.atualizaParaConferencia();
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> atualizaParaResgate() {
    int linhasAfetadas = transferenciaRepository.atualizaParaResgate();
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}