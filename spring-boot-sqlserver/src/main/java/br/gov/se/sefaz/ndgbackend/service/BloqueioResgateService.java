package br.gov.se.sefaz.ndgbackend.service;

import javax.validation.constraints.PositiveOrZero;

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
import br.gov.se.sefaz.ndgbackend.model.BloqueioResgateModel;
import br.gov.se.sefaz.ndgbackend.repository.BloqueioResgateRepository;

@Service
@Validated
public class BloqueioResgateService {

  @Autowired
  private BloqueioResgateRepository bloqueioResgateRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  private final String DATA_BLOQUEIO = "dataBloqueio";
  private final String INVALIDA = "inválida";

  public ResponseEntity<EntidadeResult> buscaPorData(String dataBloqueio) throws TransactionAbortException {
    if (!Data.isValida(dataBloqueio)) {
      throw new TransactionAbortException(DATA_BLOQUEIO, INVALIDA);
    }

    BloqueioResgateModel bloqueioResgateModel = bloqueioResgateRepository.buscaPorData(dataBloqueio);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, bloqueioResgateModel);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<BloqueioResgateModel> slice = bloqueioResgateRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(BloqueioResgateModel bloqueioResgateModel) throws TransactionAbortException {
    if (!Data.isValida(bloqueioResgateModel.getDataBloqueio())) {
      throw new TransactionAbortException(DATA_BLOQUEIO, INVALIDA);
    }

    int linhasAfetadas = bloqueioResgateRepository.cadastra(bloqueioResgateModel.getDataBloqueio(), bloqueioResgateModel.getMensagemBloqueio());
    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> atualiza(BloqueioResgateModel bloqueioResgateModel) throws TransactionAbortException {
    if (!Data.isValida(bloqueioResgateModel.getDataBloqueio())) {
      throw new TransactionAbortException(DATA_BLOQUEIO, INVALIDA);
    }

    int linhasAfetadas = bloqueioResgateRepository.atualiza(bloqueioResgateModel.getDataBloqueio(), bloqueioResgateModel.getMensagemBloqueio());
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> exclui(String dataBloqueio) throws TransactionAbortException {
    if (!Data.isValida(dataBloqueio)) {
      throw new TransactionAbortException(DATA_BLOQUEIO, INVALIDA);
    }

    int linhasAfetadas = bloqueioResgateRepository.exclui(dataBloqueio);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}