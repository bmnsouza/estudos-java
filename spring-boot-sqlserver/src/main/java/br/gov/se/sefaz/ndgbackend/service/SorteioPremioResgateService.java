package br.gov.se.sefaz.ndgbackend.service;

import java.util.List;

import javax.validation.constraints.PositiveOrZero;

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
import br.gov.se.sefaz.ndgbackend.model.SorteioPremioResgateModel;
import br.gov.se.sefaz.ndgbackend.repository.SorteioPremioResgateRepository;

@Service
@Validated
public class SorteioPremioResgateService {

  @Autowired
  private SorteioPremioResgateRepository sorteioPremioResgateRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaExpirados(@PositiveOrZero int pagina) {
    Slice<SorteioPremioResgateModel> slice = sorteioPremioResgateRepository.buscaExpirados(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaNaoExpirados() {
    List<SorteioPremioResgateModel> list = sorteioPremioResgateRepository.buscaNaoExpirados();
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, list);
  }

}