package br.gov.se.sefaz.ndgbackend.service;

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

import br.gov.se.sefaz.ndgbackend.core.Data;
import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.DocumentoFiscalModel;
import br.gov.se.sefaz.ndgbackend.repository.DocumentoFiscalRepository;

@Service
@Validated
public class DocumentoFiscalService {

  @Autowired
  private DocumentoFiscalRepository documentoFiscalRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;
  
  private UtilResult utilResult = UtilResult.getInstance();

  private final String DATA_EMISSAO_INICIO = "dataEmissaoInicio";
  private final String DATA_EMISSAO_FIM = "dataEmissaoFim";
  private final String INVALIDA = "inválida";

  public ResponseEntity<EntidadeResult> buscaPorNotaFiscal(String numeroNotaFiscal) {
    DocumentoFiscalModel documentoFiscalModel = documentoFiscalRepository.buscaPorNotaFiscal(numeroNotaFiscal);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, documentoFiscalModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorEmitente(@CNPJ String cnpjEmitente, String dataEmissaoInicio, String dataEmissaoFim, @PositiveOrZero int pagina)
    throws TransactionAbortException {
    if (!Data.isValida(dataEmissaoInicio)) {
      throw new TransactionAbortException(DATA_EMISSAO_INICIO, INVALIDA);
    }

    if (!Data.isValida(dataEmissaoFim)) {
      throw new TransactionAbortException(DATA_EMISSAO_FIM, INVALIDA);
    }

    Slice<DocumentoFiscalModel> slice = documentoFiscalRepository.buscaPorEmitente(cnpjEmitente, dataEmissaoInicio, dataEmissaoFim,
      PageRequest.of(pagina, PAGE_SIZE));

      return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorDestinatario(@CPF String cpfDestinatario, String dataEmissaoInicio, String dataEmissaoFim,
    @PositiveOrZero int pagina) throws TransactionAbortException {
    if (!Data.isValida(dataEmissaoInicio)) {
      throw new TransactionAbortException(DATA_EMISSAO_INICIO, INVALIDA);
    }

    if (!Data.isValida(dataEmissaoFim)) {
      throw new TransactionAbortException(DATA_EMISSAO_FIM, INVALIDA);
    }

    Slice<DocumentoFiscalModel> slice = documentoFiscalRepository.buscaPorDestinatario(cpfDestinatario, dataEmissaoInicio, dataEmissaoFim,
      PageRequest.of(pagina, PAGE_SIZE));

    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorEmitenteDestinatario(@CNPJ String cnpjEmitente, @CPF String cpfDestinatario, String dataEmissaoInicio,
    String dataEmissaoFim, @PositiveOrZero int pagina) throws TransactionAbortException {
    if (!Data.isValida(dataEmissaoInicio)) {
      throw new TransactionAbortException(DATA_EMISSAO_INICIO, INVALIDA);
    }

    if (!Data.isValida(dataEmissaoFim)) {
      throw new TransactionAbortException(DATA_EMISSAO_FIM, INVALIDA);
    }

    Slice<DocumentoFiscalModel> slice = documentoFiscalRepository.buscaPorEmitenteDestinatario(cnpjEmitente, cpfDestinatario, dataEmissaoInicio, dataEmissaoFim,
      PageRequest.of(pagina, PAGE_SIZE));

    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

}