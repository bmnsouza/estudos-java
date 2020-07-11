package br.gov.se.sefaz.ndgbackend.service;

import javax.validation.constraints.Positive;
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
import br.gov.se.sefaz.ndgbackend.model.SorteioModel;
import br.gov.se.sefaz.ndgbackend.repository.SorteioRepository;

@Service
@Validated
public class SorteioService {

  @Autowired
  private SorteioRepository sorteioRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  private final String DATA_INICIO = "dataInicio";
  private final String DATA_FIM = "dataFim";
  private final String DATA_REALIZACAO = "dataRealizacao";
  private final String INVALIDA = "inválida";

  public ResponseEntity<EntidadeResult> buscaPorCodigo(@Positive Integer codSorteio) {
    SorteioModel sorteioModel = sorteioRepository.buscaPorCodigo(codSorteio);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, sorteioModel);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<SorteioModel> slice = sorteioRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(SorteioModel sorteioModel) throws TransactionAbortException {
    if (!Data.isValida(sorteioModel.getDataInicio())) {
      throw new TransactionAbortException(DATA_INICIO, INVALIDA);
    }

    if (!Data.isValida(sorteioModel.getDataFim())) {
      throw new TransactionAbortException(DATA_FIM, INVALIDA);
    }

    if (!Data.isValida(sorteioModel.getDataRealizacao())) {
      throw new TransactionAbortException(DATA_REALIZACAO, INVALIDA);
    }

    int linhasAfetadas = sorteioRepository.cadastra(sorteioModel.getCodSorteio(), sorteioModel.getDataInicio(), sorteioModel.getDataFim(),
      sorteioModel.getDataRealizacao(), sorteioModel.getObservacao());

    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}