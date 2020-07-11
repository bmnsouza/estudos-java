package br.gov.se.sefaz.ndgbackend.service;

import java.util.Map;

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
import br.gov.se.sefaz.ndgbackend.model.ResgateModel;
import br.gov.se.sefaz.ndgbackend.repository.ResgateRepository;

@Service
@Validated
public class ResgateService {

  @Autowired
  private ResgateRepository resgateRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaConferencia(@PositiveOrZero int pagina) {
    Slice<ResgateModel> slice = resgateRepository.buscaConferencia(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaRealizado(String dataRelatorio, @PositiveOrZero int pagina) throws TransactionAbortException {
    if (!Data.isValida(dataRelatorio)) {
      throw new TransactionAbortException("dataRelatorio", "inválida");
    }

    Slice<ResgateModel> slice = resgateRepository.buscaRealizado(dataRelatorio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaNaoRealizado(@PositiveOrZero int pagina) {
    Slice<Map<String, String>> slice = resgateRepository.buscaNaoRealizado(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

}